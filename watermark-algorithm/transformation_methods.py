import cv2
import numpy as np
import random


def count_image_dct(image):
    return cv2.dct(image)


def copy_image(image):
    return np.copy(image)


def add_watermark(image_for_watermarking, strength):
    random.seed(2)

    appearing_pixels = __find_all_appearing_pixels(image_for_watermarking)
    for rounds_iterator in range(0, 500):
        add_watermark_in_round(image_for_watermarking, appearing_pixels, strength, rounds_iterator)

    return image_for_watermarking


def add_watermark_in_round(image_for_watermarking, appearing_pixels, strength, rounds_iterator):
    height, width = image_for_watermarking.shape
    random_value = random.random()

    for width_iterator in range(0, width):
        for height_iterator in range(0, height):
            pixel = image_for_watermarking[height_iterator][width_iterator]
            if pixel == appearing_pixels[rounds_iterator]:
                pixel = pixel + strength * random_value * pixel
                image_for_watermarking[height_iterator][width_iterator] = pixel
                return


def __find_all_appearing_pixels(image_to_analyze):
    height, width = image_to_analyze.shape
    appearing_pixels = []
    for width_iterator in range(0, width):
        for height_iterator in range(0, height):
            appearing_pixels.append(image_to_analyze[height_iterator][width_iterator])
    appearing_pixels.sort()
    return appearing_pixels


def inverse_dct_to_image(dtc_image):
    return cv2.idct(dtc_image)


def merge_rgb_image(red_layer, green_layer, blue_layer):
    height, width = red_layer.shape
    watermarked_image = np.zeros((height, width, 3), dtype=float)
    for width_iterator1 in range(0, width):
        for height_iterator1 in range(0, height):
            watermarked_image[height_iterator1, width_iterator1, 0] = red_layer[height_iterator1, width_iterator1]
            watermarked_image[height_iterator1, width_iterator1, 1] = green_layer[height_iterator1, width_iterator1]
            watermarked_image[height_iterator1, width_iterator1, 2] = blue_layer[height_iterator1, width_iterator1]
    return watermarked_image
