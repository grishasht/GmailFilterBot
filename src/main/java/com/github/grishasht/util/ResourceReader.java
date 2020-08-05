package com.github.grishasht.util;

import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ResourceReader
{
    public interface ResourceParser<T>
    {
        T parse(byte[] resourceBytes) throws Exception;
    }

    public static byte[] readAllBytesFromResources(String resourceName) throws IOException
    {
        final URL resourceUrl = ResourceReader.class.getClassLoader().getResource(resourceName);
        try
        {
            final URI resourceUri = resourceUrl.toURI();
            final Path resourcePath = Paths.get(resourceUri);
            return Files.readAllBytes(resourcePath);
        }
        catch (Exception ex)
        {
            throw new IOException("Can't read resource = " + resourceName, ex);
        }
    }

    public static <T> T readAllBytesFromResourcesAs(String resourceName, ResourceParser<T> resourceParser)
    {
        try
        {
            final byte[] bytes = readAllBytesFromResources(resourceName);
            return resourceParser.parse(bytes);
        }
        catch (Exception ex)
        {
            throw new RuntimeException(ex);
        }
    }
}