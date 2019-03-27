using System;
using System.Collections.Generic;
using System.Net.Http;
using System.Net.Http.Headers;

namespace CustomerPortal
{

    public class WebService
    {
        private string getAllURL = "http://localhost:8080/RESTSample/rest/Customers";
        private string urlParameters = "";

        public void getAllCustomers()
        {
            HttpClient client = new HttpClient();
            client.BaseAddress = new Uri(getAllURL);

            // Add an Accept header for JSON format.
            client.DefaultRequestHeaders.Accept.Add(
            new MediaTypeWithQualityHeaderValue("application/json"));

            // List data response.
            HttpResponseMessage response = client.GetAsync(urlParameters).Result;  // Blocking call!
            if (response.IsSuccessStatusCode)
            {
                // Parse the response body. Blocking!
                var dataObjects = response.Content.ReadAsAsync<IEnumerable<Customer>>().Result;
                foreach (var d in dataObjects)
                {
                    Console.WriteLine("{0}", d.firstName);
                }
            }
            else
            {
                Console.WriteLine("{0} ({1})", (int)response.StatusCode, response.ReasonPhrase);
            }
        }
    }
}
