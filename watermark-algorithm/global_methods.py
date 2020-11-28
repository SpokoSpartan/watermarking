import cv2
import numpy as np

from transformation_methods import resize_image

TESTING_ALGORITHM = 0


def read_image(name):
    return cv2.imread(name)


def read_grey_image(name):
    return cv2.imread(name, 0)


def to_binary_array(image, extension):
    success, encoded_image = cv2.imencode(ext=extension, img=image)
    return encoded_image.tobytes()


def prepare_window():
    if TESTING_ALGORITHM:
        cv2.namedWindow("Watermarking", cv2.WINDOW_AUTOSIZE)


def print_plot(plot_name, data):
    if TESTING_ALGORITHM:
        cv2.imshow(plot_name, data)


def wait_for_key():
    if TESTING_ALGORITHM:
        cv2.waitKey(0)


def __normalize_shape(shape):
    return shape if shape[0] > shape[1] else (shape[1], shape[0])


def get_shape_divided_by(shape, divider):
    return int(shape[0] / divider), int(shape[1] / divider)


def reverse_shape(shape):
    return shape[1], shape[0]


def find_shape_similarities(watermark1, watermark2):
    watermark_height, watermark_width = watermark1.shape
    watermark2 = resize_image(watermark2, (watermark_width, watermark_height))
    return cv2.matchTemplate(watermark1.astype(np.float32), watermark2.astype(np.float32), cv2.TM_CCOEFF_NORMED)
