package urlservice;

import java.net.URI;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import model.ShortenerURLInfo;
import urlprocess.ShortenerURLProvider;
import util.ShortenerURLHelpConstants;
import util.ShortenerURLHelper;

@Path("/")
public class ShortenerURLService {
	private ShortenerURLProvider shortUrlprovider = new ShortenerURLProvider();

	@POST
	@Path("/regshorturl")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response registerURL(@Context UriInfo uriInfo, @HeaderParam("Authorization") String header, ShortenerURLInfo urlInfo) {
		urlInfo.setUrl(urlInfo.getUrl().trim());

		if ("".equals(urlInfo.getUrl()))
			return Response.status(415).build();

		if (!Integer.valueOf(301).equals(urlInfo.getRedirectType()) && !Integer.valueOf(
				301).equals(urlInfo.getRedirectType()))
			urlInfo.setRedirectType(302);
		ShortenerURLInfo existing = shortUrlprovider.getUrlInfo(urlInfo.getUrl());

		if (existing == null) {
			urlInfo.setShortUrl(uriInfo.getBaseUri() + ShortenerURLHelper.generateRandomString(6));
			while (shortUrlprovider.shortUrlExists(urlInfo.getShortUrl()))
				urlInfo.setShortUrl(uriInfo.getBaseUri() + ShortenerURLHelper.generateRandomString(6));
			shortUrlprovider.createURL(urlInfo);
			return Response.status(201).entity(ShortenerURLHelper.generateURLResponse(urlInfo.getShortUrl())).build();
		}

		if (!existing.getRedirectType().equals(urlInfo.getRedirectType()))
			shortUrlprovider.updateURL(urlInfo);

		return Response.status(200).entity(ShortenerURLHelper.generateURLResponse(existing.getShortUrl())).build();
	}


	@GET
	@Path("/{shortUrl}")
	public Response redirect(@Context UriInfo uriInfo, @PathParam("shortUrl") String shortUrl) {
		ShortenerURLInfo shortUrlInfo = shortUrlprovider.getUrlInfoByShortUrl(uriInfo.getBaseUri() + shortUrl);
		if (shortUrlInfo != null) {
			shortUrlprovider.counterInc(shortUrlInfo.getUrl());
			return Response.seeOther(URI.create(shortUrlInfo.getUrl())).status(shortUrlInfo.getRedirectType()).build();
		}

		return Response.status(404).build();
	}

	@GET
	@Path("/help")
	@Produces(MediaType.TEXT_HTML)
	public String help() {
		return ShortenerURLHelpConstants.HELP_PAGE;
	}
}