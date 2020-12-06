import PIL.Image as Img

from global_methods import read_image, SAVE_DIRECTORY


def get_image(url):
    image_url = url
    filename = image_url.split("=")[-1]
    return filename, read_image(SAVE_DIRECTORY + filename)


def upload_image(image, filename):
    # Reverse colors -> BGR to RGB
    image = image[..., [2, 1, 0]]
    im = Img.fromarray(image, 'RGB')
    im.save(SAVE_DIRECTORY + 'watermarked-' + filename)
    return 'http://localhost:8080/image/get?name=watermarked-' + filename
