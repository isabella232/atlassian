package com.onelogin.jira.saml;

import java.net.URLEncoder;

public class TstAuthRequest {
private static final String jiraHost = "localhost";
//private static final String oneloginSamlEndpoint = "http://onelogin.local/saml/signon/39854";
private static final String oneloginSamlEndpoint = "https://app.onelogin.com/trust/saml2/http-post/sso/177115";
    
	public static void main(String[] argv)
	{
		try{
			String reqString = "";

			// The appSettings object contain application specific settings used by the SAML library
			AppSettings appSettings = new AppSettings();

			// Set the URL of the consume.jsp (or similar) file for this application. The SAML Response will be posted to this URL
			appSettings.setAssertionConsumerServiceUrl("http://"+jiraHost+":8080/login.jsp");

			// Set the issuer of the authentication request. This would usually be the URL of the issuing web application
			appSettings.setIssuer("http://"+jiraHost+":8080/secure/Dashboard.jspa");

			// The accSettings object contains settings specific to the users account. At this point, your application must have identified the users origin
			AccountSettings accSettings = new AccountSettings();

			// The URL at the Identity Provider where the authentication request should be sent
			accSettings.setIdpSsoTargetUrl(oneloginSamlEndpoint);

			// Generate an AuthRequest and send it to the identity provider
			AuthRequest authReq = new AuthRequest(appSettings, accSettings);
			
                        System.out.println(authReq.getRequest(1));
                        
                        reqString = accSettings.getIdp_sso_target_url()+
                    			"?SAMLRequest=" +
                    			URLEncoder.encode(authReq.getRequest(AuthRequest.base64),"UTF-8");
                        System.out.println(reqString);
		}
		catch(Exception ex)
		{
			System.out.println(ex.getMessage());
		}
	}
}
