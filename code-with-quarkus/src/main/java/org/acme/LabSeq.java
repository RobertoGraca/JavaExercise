package org.acme;

import java.math.BigInteger;
import java.util.HashMap;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/labseq")
public class LabSeq {

    private HashMap<Integer, String> cache = new HashMap<Integer, String>();

    @Path("/{num}")
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String response(@PathParam("num") int num) {

        // If the number is not non-negative, skip it
        if (num < 0) {
            return "Not a valid number";
        }

        // Initialize result string
        String result = "";

        // Checks cache for the presence of a previous key
        if (cache.containsKey(num)) {
            // If the key exists, uses the previous result
            result = cache.get(num);
        } else {
            // If the key does not exist, computes new result and stores it in the cache
            result = seqResult(num);
            cache.put(num, result);
        }

        return result;
    }

    // Method to compute new result
    public String seqResult(int num) {

        // Initialize result
        BigInteger result = new BigInteger("0");

        // Create small cache that stores the last 4 computed results
        HashMap<Integer, BigInteger> four_values = new HashMap<Integer, BigInteger>();
        four_values.put(0, new BigInteger("0"));
        four_values.put(1, new BigInteger("1"));
        four_values.put(2, new BigInteger("0"));
        four_values.put(3, new BigInteger("1"));

        // Performs iterations until the speficied number of the sequence
        for (int i = 0; i < num + 1; i++) {
            // Updates the cache with the new number and remove the last
            result = four_values.get(i);
            four_values.put(i + 4, result.add(four_values.get(i + 1)));
            four_values.remove(i);
        }

        return result.toString();
    }
}
