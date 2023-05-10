# LambdaPullDataFromAPI POC
- Lambda call API and save data to S3

## Command
```bash
# build infra
# grant execution permission to your script
chmod 755 setup_infra.sh
# please replace with your bucket nam
./setup_infra.sh jsonplaceholder-1 --zip-file fileb://myDeploymentPackage.zip

# run script (local)
```

## Ref
- https://www.startdataengineering.com/post/pull-data-from-api-using-lambda-s3/