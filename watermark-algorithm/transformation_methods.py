import cv2
import numpy as np


def count_image_dct(image):
    return cv2.dct(image)


def inverse_dct_to_image(dtc_image):
    return cv2.idct(dtc_image)


def copy_image(image):
    return np.copy(image)


def resize_image(image, shape):
    return cv2.resize(image, shape)


# to be sure we can divide it to blocks
def standardize_image_to_blocks(image, block_size):
    shape = ((image.shape[1] // block_size) * block_size, (image.shape[0] // block_size) * block_size)
    return cv2.resize(image, shape)


def get_sub_image(image, block, start_height, start_width):
    return image[start_height * block: (start_height + 1) * block, start_width * block: (start_width + 1) * block]


def assign_block_to_image(image, block, start_height, start_width, data):
    image[start_height * block: (start_height + 1) * block, start_width * block: (start_width + 1) * block] = data


def prepare_binary_image(image):
    return np.round(image / 255)


def prepare_image_as_file(image):
    return image * 255


def get_matrix_of_zeros(shape):
    return np.zeros(shape)


def merge_rgb_image(red_layer, green_layer, blue_layer):
    height, width = red_layer.shape
    watermarked_image = np.zeros((height, width, 3), dtype=float)
    for width_iterator1 in range(0, width):
        for height_iterator1 in range(0, height):
            watermarked_image[height_iterator1, width_iterator1, 0] = red_layer[height_iterator1, width_iterator1]
            watermarked_image[height_iterator1, width_iterator1, 1] = green_layer[height_iterator1, width_iterator1]
            watermarked_image[height_iterator1, width_iterator1, 2] = blue_layer[height_iterator1, width_iterator1]
    return watermarked_image


def to_array(vector):
    return np.array(vector)
