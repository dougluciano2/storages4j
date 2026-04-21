package io.dougluciano.storages4j.v2.configuration;

public final class Storages4JConfiguration {

    private final String bucket;
    private final String accessKey;
    private final String secretKey;
    private final boolean enableMetadata;
    private final boolean useUniquePath;
    private final StorageProvider provider;

    private Storages4JConfiguration(Builder builder) {
        this.bucket = builder.bucket;
        this.accessKey = builder.accessKey;
        this.secretKey = builder.secretKey;
        this.enableMetadata = builder.enableMetadata;
        this.useUniquePath = builder.useUniquePath;
        this.provider = builder.provider;
    }

    public static Builder builder() {
        return new Builder();
    }

    public String getBucket() {
        return this.bucket;
    }

    public String getAccessKey() {
        return this.accessKey;
    }

    public String getSecretKey(){
        return this.secretKey;
    }

    public boolean getEnableMetadata() {
        return this.enableMetadata;
    }

    public boolean getUseUniquePath() {
        return this.useUniquePath;
    }

    public StorageProvider getProvider() {
        return this.provider;
    }

    @Override
    public String toString() {
        return "StorageConfiguration{" +
                "bucket='" + getBucket() + '\'' +
                ", accessKey='" + getAccessKey() + '\'' +
                ", secretKey='" + getSecretKey() + '\'' +
                ", enableMetadata=" + getEnableMetadata() +
                ", useUniquePath=" + getUseUniquePath() +
                ", provider=" + getProvider() +
                '}';
    }

    public static class Builder {
        private String bucket;
        private String accessKey;
        private String secretKey;
        private boolean enableMetadata;
        private boolean useUniquePath;
        private StorageProvider provider;

        public Builder bucket(String bucket) {
            this.bucket = bucket;
            return this;
        }

        public Builder accessKey(String accessKey) {
            this.accessKey = accessKey;
            return this;
        }

        public Builder secretKey(String secretKey) {
            this.secretKey = secretKey;
            return this;
        }

        public Builder enableMetadata(boolean enableMetadata) {
            this.enableMetadata = enableMetadata;
            return this;
        }

        public Builder useUniquePath(boolean useUniquePath) {
            this.useUniquePath = useUniquePath;
            return this;
        }

        public Builder provider(StorageProvider provider) {
            this.provider = provider;
            return this;
        }

        public Storages4JConfiguration build() {

            if(provider == null){ throw new IllegalArgumentException("Provider must not be null or blank!");}
            if(bucket == null || bucket.isBlank()){ throw new IllegalArgumentException("Bucket must not be null or blank!");}
            if(accessKey == null || accessKey.isBlank()){ throw new IllegalArgumentException("Access key must not be null or blank!");}
            if(secretKey == null || secretKey.isBlank()){ throw new IllegalArgumentException("Secret key must not be null or blank!");}

            return new Storages4JConfiguration(this);
        }

    }
}

