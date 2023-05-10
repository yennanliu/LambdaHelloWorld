"""
https://www.startdataengineering.com/post/pull-data-from-api-using-lambda-s3/
"""

import urllib3
import json

CHUNK_SIZE = 10000  # determined based on API, memory constraints, experimentation
API_URL = "http://jsonplaceholder.typicode.com/posts"

def get_num_records():

    # Dummy function, to replicate GET http://jsonplaceholder.typicode.com/number_of_users call
    return 100000


def get_data(start_user_id, end_user_id, get_path=API_URL):

    http = urllib3.PoolManager()
    data = {"userId": None, "id": None, "title": None, "body": None}
    try:
        r = http.request(
            "GET",
            get_path,
            retries=urllib3.util.Retry(3),
            fields={"start_user_id": start_user_id, "end_user_id": end_user_id},
        )
        data = json.loads(r.data.decode("utf8").replace("'", '"'))
    except KeyError as e:
        print(f"Wrong format url {get_path}", e)
    except urllib3.exceptions.MaxRetryError as e:
        print(f"API unavailable at {get_path}", e)
    return data


def parse_data(json_data):

    return f'{json_data.get("userId")},{json_data["id"]},"{json_data["title"]}"\n'


def write_to_local(data, output_file, loc="/Users/yenanliu/LambdaHelloWorld/LambdaPullDataFromAPI"):

    file_name = loc + "/" + str(output_file)
    print(f"file_name = {file_name}")
    with open(file_name, "w") as file:
        for elt in data:
            file.write(parse_data(elt))
    return file_name


def download_data(N):

    for i in range(0, N, CHUNK_SIZE):
        data = get_data(i, i + CHUNK_SIZE)
        write_to_local(data, i // CHUNK_SIZE)

if __name__ == '__main__':
    data = get_data(1, 10)
    #print(f"data = {data}")
    write_to_local(data, "jsonplaceholder_output.txt")
