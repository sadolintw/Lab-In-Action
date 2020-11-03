package me.lab.in.action.azure.service.impl;

import java.io.IOException;
import java.net.URISyntaxException;
import java.security.InvalidKeyException;

import me.lab.in.action.azure.service.AzureService;
import org.springframework.stereotype.Service;

import com.microsoft.azure.storage.CloudStorageAccount;
import com.microsoft.azure.storage.OperationContext;
import com.microsoft.azure.storage.StorageException;
import com.microsoft.azure.storage.blob.BlobContainerPublicAccessType;
import com.microsoft.azure.storage.blob.BlobRequestOptions;
import com.microsoft.azure.storage.blob.CloudBlobClient;
import com.microsoft.azure.storage.blob.CloudBlobContainer;
import com.microsoft.azure.storage.blob.CloudBlockBlob;
import com.microsoft.azure.storage.file.CloudFile;
import com.microsoft.azure.storage.file.CloudFileClient;
import com.microsoft.azure.storage.file.CloudFileDirectory;
import com.microsoft.azure.storage.file.CloudFileShare;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class AzureServiceImpl implements AzureService {
	
	@Override
	public CloudStorageAccount getCloudStorageAccount(String account, String key) throws InvalidKeyException, URISyntaxException {
		String storageConnectionString =
			    String.format("DefaultEndpointsProtocol=http;AccountName=%s;AccountKey=%s", account, key);
		return CloudStorageAccount.parse(storageConnectionString);
	}
	
	@Override
	public CloudFileShare getCloudFileShare(CloudFileClient fileClient, String shareReference) throws URISyntaxException, StorageException {
		CloudFileShare share = fileClient.getShareReference(shareReference);
		
		if (share.createIfNotExists()) {
		    log.info("New share created {}", shareReference);
		}
		return share;
	}
	
	@Override
	public CloudFileDirectory getCloudFileDirectory(CloudFileShare share, String referenceDir) throws StorageException, URISyntaxException {
		//Get a reference to the root directory for the share.
		CloudFileDirectory rootCloudFileDir = share.getRootDirectoryReference();

		//Get a reference to the sampledir directory
		CloudFileDirectory referenceCloudFileDir = rootCloudFileDir.getDirectoryReference(referenceDir);

		if (referenceCloudFileDir.createIfNotExists()) {
		    log.info("{} created", referenceDir);
		} else {
		    log.info("{} already exists", referenceDir);
		}
		return referenceCloudFileDir;
	}
	
	@Override
	public void uploadFile(CloudFileShare share, String referenceDir, String filePath, String fileName) throws StorageException, URISyntaxException, IOException {
		CloudFileDirectory destDir = getCloudFileDirectory(share, referenceDir);
        CloudFile cloudFile = destDir.getFileReference(fileName);
        cloudFile.uploadFromFile(filePath);
	}
	
	@Override
	public CloudBlobContainer getCloudBlobContainer(CloudBlobClient blobClient, String containerName) throws URISyntaxException, StorageException {
		
		CloudBlobContainer container = blobClient.getContainerReference(containerName);
		
		if(container.createIfNotExists(BlobContainerPublicAccessType.CONTAINER, new BlobRequestOptions(), new OperationContext())) {
			log.info("{} created", containerName);
		}else {
			log.info("{} already exists", containerName);
		}
		
		return container;
	}
	
	@Override
	public void uploadFile(CloudBlobContainer container, String filePath, String fileName) throws URISyntaxException, StorageException, IOException {
		CloudBlockBlob blob = container.getBlockBlobReference(fileName);
		blob.uploadFromFile(filePath);
	}
}
