import requests # to get image from the web
import shutil # to save it locally
import cv2

def get_image(url):
    image_url = url
    filename = image_url.split("/")[-1]
    r = requests.get(image_url, stream=True)

    # Check if the image was retrieved successfully
    if r.status_code == 200:
        # Set decode_content value to True, otherwise the downloaded image file's size will be zero.
        r.raw.decode_content = True

        # Open a local file with wb ( write binary ) permission.
        with open(filename, 'wb') as f:
            shutil.copyfileobj(r.raw, f)

        print('Image sucessfully Downloaded: ', filename)
        return cv2.imread(filename);
    else:
        print('Image Couldn\'t be retreived')
        return ("Error")
