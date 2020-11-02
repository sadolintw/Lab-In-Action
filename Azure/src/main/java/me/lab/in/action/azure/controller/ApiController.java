package me.lab.in.action.azure.controller;

import com.azure.storage.common.StorageSharedKeyCredential;
import com.azure.storage.file.datalake.DataLakeDirectoryClient;
import com.azure.storage.file.datalake.DataLakeFileSystemClient;
import com.azure.storage.file.datalake.DataLakeServiceClient;
import com.azure.storage.file.datalake.DataLakeServiceClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ApiController {

    @Value("${azure.username}")
    String username;

    @Value("${azure.key}")
    String key;

    static public DataLakeServiceClient GetDataLakeServiceClient(String accountName, String accountKey) {

        StorageSharedKeyCredential sharedKeyCredential =
                new StorageSharedKeyCredential(accountName, accountKey);

        DataLakeServiceClientBuilder builder = new DataLakeServiceClientBuilder();

        builder.credential(sharedKeyCredential);
        builder.endpoint("https://" + accountName + ".dfs.core.windows.net");

        return builder.buildClient();
    }

    static public DataLakeFileSystemClient CreateFileSystem(DataLakeServiceClient serviceClient){

        return serviceClient.getFileSystemClient("my-file-system");
    }

    static public DataLakeDirectoryClient CreateDirectory(DataLakeServiceClient serviceClient, String fileSystemName) {

        DataLakeFileSystemClient fileSystemClient =
                serviceClient.getFileSystemClient(fileSystemName);

        DataLakeDirectoryClient directoryClient =
                fileSystemClient.createDirectory("my-directory");

        return directoryClient.createSubdirectory("my-subdirectory");
    }

    @GetMapping("test")
    public Object test() {
        System.out.println("start");
        DataLakeServiceClient client = GetDataLakeServiceClient(username, key);
        System.out.println("1");
        DataLakeFileSystemClient client2 = CreateFileSystem(client);
        System.out.println("2");
        CreateDirectory(client, "my-file-system");
        System.out.println("end");
        return "OK";
    }
}
