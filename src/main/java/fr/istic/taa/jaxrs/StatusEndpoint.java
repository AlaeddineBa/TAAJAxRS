/**
 * JBoss, Home of Professional Open Source
 * Copyright Red Hat, Inc., and individual contributors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package fr.istic.taa.jaxrs;


import java.io.IOException;
import java.util.logging.Logger;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import fr.istic.taa.jaxrs.domain.Person;
import twitter4j.DirectMessage;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

@Path("/status")
public class StatusEndpoint {

    private static final Logger logger = Logger.getLogger(StatusEndpoint.class.getName());

    @GET
    public Response getStatus() {

        return Response.status(Response.Status.OK).entity("JO").build();
    }
    
    @GET
    @Path("/test")
    public String helloWorld() {

        return "hello";
    }

    @GET
    @Path("/person")
    @Produces(MediaType.APPLICATION_JSON)
    public Person getPerson() {
    	Person p = new Person();
    	p.setName("Alaeddine");
    	p.setFirstName("t");
        return p;
    }

    @POST
    @Path("/person")
    @Consumes(MediaType.APPLICATION_JSON)
    public void addPerson(Person p) {
    	System.err.println(p.getName());
    	System.err.println(p.getFirstName());
    }
    
    @GET
    @Path("/swapi")
    @Produces(MediaType.APPLICATION_JSON)
    public String getSwapi() throws IOException {CloseableHttpClient httpclient = HttpClients.createDefault();
    HttpGet httpget = new HttpGet("http://swapi.co/api/people/1/");
    CloseableHttpResponse response = httpclient.execute(httpget);
    System.out.println(response.toString());
    try {
        
    } finally {
        response.close();
    }
	return null;

    }
    
    
    @GET
	@Path("/postTweet")
	@Produces(MediaType.APPLICATION_JSON)
	public String getTweet() throws IOException, TwitterException {
		ConfigurationBuilder cb = new ConfigurationBuilder();
		cb.setDebugEnabled(true)
		.setOAuthConsumerKey("jUTzyyI3zjBqiKQqhfQyFeqpm")
		.setOAuthConsumerSecret("gMkOSO9EYqlzrnq35kdoZIqvX12sJwP1wHKMqo6uYbvq2q0LGl")
		.setOAuthAccessToken("89767958-C2cLIz69SpdR6wmQGdzE8rQSXAMzIVBpYOuaZGmHQ")
		.setOAuthAccessTokenSecret("oG7FQJMQsX2OHKxcaHjUxgZI94ZqUShGi0qCAsI50xfpZ");
		TwitterFactory tf = new TwitterFactory(cb.build());
		Twitter twitter = tf.getInstance();
		String latestStatus = "TEST55555555";
	    Status status = twitter.updateStatus(latestStatus);
	    System.out.println("Successfully updated the status to [" + status.getText() + "].");
	    return "Successfully updated status to " + status.getText();
		
	}
    
    @GET
   	@Path("/sendTweet")
   	@Produces(MediaType.APPLICATION_JSON)
   	public String sendTweet() throws IOException, TwitterException {
   		ConfigurationBuilder cb = new ConfigurationBuilder();
   		cb.setDebugEnabled(true)
   		.setOAuthConsumerKey("jUTzyyI3zjBqiKQqhfQyFeqpm")
   		.setOAuthConsumerSecret("gMkOSO9EYqlzrnq35kdoZIqvX12sJwP1wHKMqo6uYbvq2q0LGl")
   		.setOAuthAccessToken("89767958-C2cLIz69SpdR6wmQGdzE8rQSXAMzIVBpYOuaZGmHQ")
   		.setOAuthAccessTokenSecret("oG7FQJMQsX2OHKxcaHjUxgZI94ZqUShGi0qCAsI50xfpZ");
   		TwitterFactory tf = new TwitterFactory(cb.build());
   		
   		
   		Twitter twitter = tf.getInstance();
   		
   	    String recipientId;
   		recipientId = "@nassssssiim";
   		
   		String message;
   		message = "test";
   	    
   	    DirectMessage message1 = twitter.sendDirectMessage(recipientId, message);
   	    System.out.println( " to @" + message1.getRecipientScreenName());
   	    return " to @" + message1.getRecipientScreenName();
   		
   	}
    
    
   
}

