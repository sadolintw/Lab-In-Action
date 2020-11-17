package me.lab.in.action.azure.service;

import java.io.IOException;
import java.net.URISyntaxException;
import java.security.InvalidKeyException;

import com.microsoft.azure.storage.CloudStorageAccount;
import com.microsoft.azure.storage.StorageException;
import com.microsoft.azure.storage.blob.CloudBlobClient;
import com.microsoft.azure.storage.blob.CloudBlobContainer;
import com.microsoft.azure.storage.file.CloudFileClient;
import com.microsoft.azure.storage.file.CloudFileDirectory;
import com.microsoft.azure.storage.file.CloudFileShare;

public interface AzureService {
	
	CloudStorageAccount getCloudStorageAccount(String account, String key) throws InvalidKeyException, URISyntaxException;
	
	public CloudFileShare getCloudFileShare(CloudFileClient fileClient, String shareReference) throws URISyntaxException, StorageException;
	
	public CloudFileDirectory getCloudFileDirectory(CloudFileShare share, String referenceDir) throws StorageException, URISyntaxException;
	
	public void uploadFile(CloudFileShare share, String referenceDir, String filePath, String fileName) throws StorageException, URISyntaxException, IOException;
	
	public CloudBlobContainer getCloudBlobContainer(CloudBlobClient blobClient, String containerName) throws URISyntaxException, StorageException;
	
	public void uploadFile(CloudBlobContainer container, String filePath, String fileName) throws URISyntaxException, StorageException, IOException;
}
