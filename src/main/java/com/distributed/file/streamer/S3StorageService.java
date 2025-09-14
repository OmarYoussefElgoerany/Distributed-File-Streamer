package com.distributed.file.streamer;


// Handles AWS S3 operations
@Service
public class S3StorageService {

    private final AmazonS3 amazonS3;
    private final String bucketName = "your-bucket-name";

    public S3StorageService(AmazonS3 amazonS3) {
        this.amazonS3 = amazonS3;
    }

    public String storeFile(MultipartFile file) {
        String key = UUID.randomUUID() + "_" + file.getOriginalFilename();
        try {
            amazonS3.putObject(bucketName, key, file.getInputStream(), new ObjectMetadata());
            return amazonS3.getUrl(bucketName, key).toString();
        } catch (IOException e) {
            throw new RuntimeException("Failed to upload file", e);
        }
    }
}
