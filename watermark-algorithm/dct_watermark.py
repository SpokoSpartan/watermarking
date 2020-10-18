import cv2
import numpy as np

from global_methods import read_image, print_plot, prepare_window
from transformation_methods import count_image_dct, copy_image, add_watermark, inverse_dct_to_image, merge_rgb_image


def add_watermark_to_image(rgb_image, strength):
    red_image = rgb_image[:, :, 0]
    green_image = rgb_image[:, :, 1]
    blue_image = rgb_image[:, :, 2]

    red_watermarked_image = add_watermark_to_one_image_layer(red_image, strength)
    green_watermarked_image = add_watermark_to_one_image_layer(green_image, strength)
    blue_watermarked_image = add_watermark_to_one_image_layer(blue_image, strength)

    inv_red_dct = inverse_dct_to_image(red_watermarked_image)
    inv_green_dct = inverse_dct_to_image(green_watermarked_image)
    inv_blue_dct = inverse_dct_to_image(blue_watermarked_image)

    return merge_rgb_image(inv_red_dct, inv_green_dct, inv_blue_dct)


def add_watermark_to_one_image_layer(colored_image, strength):
    float_image = np.float64(colored_image) / 255.0
    dct_image = count_image_dct(float_image)
    image_dct_copy = copy_image(dct_image)
    return add_watermark(image_dct_copy, strength)


prepare_window()
image = read_image("images/image.jpg")
print_plot("Image", image)
watermarkedImage = add_watermark_to_image(image, 3.05)
print_plot("Watermarked image", watermarkedImage)
k = cv2.waitKey(0)
