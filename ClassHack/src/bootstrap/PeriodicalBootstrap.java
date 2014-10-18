package bootstrap;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.PropertiesCredentials;
import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;

public class PeriodicalBootstrap {
	private AmazonDynamoDBClient client;
	private static AWSCredentials credentials;
	
	public static void main(String[] args) throws FileNotFoundException, IllegalArgumentException, IOException
	{
		ProfileCredentialsProvider credentialProvider = new ProfileCredentialsProvider();
		credentials = credentialProvider.getCredentials();
	}
	
	
}
