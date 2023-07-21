"""

https://docs.aws.amazon.com/code-library/latest/ug/python_3_kinesis_code_examples.html

"""

import boto3


class KinesisStream:
    """Encapsulates a Kinesis stream."""
    def __init__(self, kinesis_client):
        """
        :param kinesis_client: A Boto3 Kinesis client.
        """
        self.kinesis_client = kinesis_client
        self.name = None
        self.details = None
        self.stream_exists_waiter = kinesis_client.get_waiter('stream_exists')

    def put_record(self, data, partition_key):
        """
        Puts data into the stream. The data is formatted as JSON before it is passed
        to the stream.

        :param data: The data to put in the stream.
        :param partition_key: The partition key to use for the data.
        :return: Metadata about the record, including its shard ID and sequence number.
        """
        try:
            response = self.kinesis_client.put_record(
                StreamName=self.name,
                Data=json.dumps(data),
                PartitionKey=partition_key)
            logger.info("Put record in stream %s.", self.name)
        except ClientError:
            logger.exception("Couldn't put record in stream %s.", self.name)
            raise
        else:
            return response

if __name__ == '__main__':
    kinesis_client = boto3.client('kinesis')
    print (f"kinesis_client = {kinesis_client}")
    k_stream = new KinesisStream()
    data = dict("a": 1, "b": 2, "c": 3)
    PARTITION_KEY = "key_1"
    for i in range(10):
        k_stream.put_record(data, PARTITION_KEY)