package me.lab.in.action.azure;

import com.microsoft.azure.storage.CloudStorageAccount;
import com.microsoft.azure.storage.StorageException;
import com.microsoft.azure.storage.blob.CloudBlobClient;
import com.microsoft.azure.storage.blob.CloudBlobContainer;
import com.microsoft.azure.storage.file.CloudFileClient;
import com.microsoft.azure.storage.file.CloudFileShare;
import me.lab.in.action.azure.service.AzureService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.net.URISyntaxException;
import java.security.InvalidKeyException;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {ApplicationTests.TestApplication.class})
public class ApplicationTests {

	@Autowired
	AzureService azureService;

	@Value("${azure.username}")
	String account;

	@Value("${azure.key}")
	String key;

	@Value("${azure.ref}")
	String refName;

	@SpringBootApplication(exclude={DataSourceAutoConfiguration.class})
	public static class TestApplication{
		public static void main(String[] args) {
			SpringApplication.run(TestApplication.class, args);
		}
	}

	@Test
	public void testAzureFileUpload() throws StorageException, URISyntaxException, IOException, InvalidKeyException {
		CloudStorageAccount cloudStorageAccount = azureService.getCloudStorageAccount(account, key);

		CloudFileClient fileClient = cloudStorageAccount.createCloudFileClient();

		CloudFileShare share = azureService.getCloudFileShare(fileClient, refName);

		String referenceDir = "sampledir/subsampledir";

		final String filePath = "C:\\temp\\test.jpg";

		String fileName = "test.jpg";

		azureService.uploadFile(share, "sampledir/subsampledir", filePath, fileName);
	}

	@Test
	public void testAzureBlobUpload() throws InvalidKeyException, URISyntaxException, StorageException, IOException {
		CloudStorageAccount cloudStorageAccount = azureService.getCloudStorageAccount(account, key);

		CloudBlobClient blobClient = cloudStorageAccount.createCloudBlobClient();

		CloudBlobContainer container = azureService.getCloudBlobContainer(blobClient, refName);

		final String filePath = "C:\\temp\\test.jpg";

		azureService.uploadFile(container, filePath, "temp/test.jpg");
	}

}
