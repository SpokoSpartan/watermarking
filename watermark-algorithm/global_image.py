import requests  # to get image from the web

import cv2
import numpy as np

from cloudinary import uploader

from global_methods import to_binary_array


def get_image(url):
    image_url = url
    filename = image_url.split("/")[-1]

    response = requests.get(image_url, stream=True)

    # Check if the image was retrieved successfully
    if response.status_code == 200:
        # Set decode_content value to True, otherwise the downloaded image file's size will be zero.
        response.raw.decode_content = True

        # 16MB
        image_bytes = response.raw.read(16777216)

        image = cv2.imdecode(np.frombuffer(image_bytes, np.uint8), -1)
        print('Image successfully downloaded: ', filename)
        return filename, image
    else:
        raise Exception('Image Couldn\'t be retrieved')


def upload_image(image, filename):
    binary_image = to_binary_array(image, '.' + filename.split(".")[-1])
    image_url = uploader.upload(binary_image, cloud_name='drdbdppyy', api_key='855766653176694', api_secret='yR_DphHrxGPSwFEJpfav-dlgBP8')
    return image_url["url"]
