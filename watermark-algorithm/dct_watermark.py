from global_methods import read_image, print_plot, prepare_window, get_shape_divided_by, \
    wait_for_key, reverse_shape, read_grey_image
from transformation_methods import count_image_dct, inverse_dct_to_image, merge_rgb_image, \
    standardize_image_to_blocks, resize_image, get_sub_image, assign_block_to_image, get_matrix_of_zeros, \
    prepare_binary_image


def add_watermark_to_image(rgb_image, watermark):
    red_image = rgb_image[:, :, 0]
    green_image = rgb_image[:, :, 1]
    blue_image = rgb_image[:, :, 2]

    # red_watermarked_image = add_watermark_to_one_image_layer(red_image, watermark)
    green_watermarked_image = add_watermark_to_one_image_layer(green_image, watermark)
    # blue_watermarked_image = add_watermark_to_one_image_layer(blue_image, watermark)

    return merge_rgb_image(red_image, green_watermarked_image, blue_image)


def add_watermark_to_one_image_layer(colored_image, watermark):
    # make sure that watermark is 8 time smaller than image
    watermark_shape = get_shape_divided_by(colored_image.shape, block_size)
    watermark_shape = reverse_shape(watermark_shape)
    watermark = resize_image(watermark, watermark_shape)

    watermark = prepare_binary_image(watermark)

    colored_image = colored_image.astype('float32')

    watermark_height, watermark_width = watermark.shape

    image_wm = get_matrix_of_zeros(colored_image.shape)
    for height_iter in range(watermark_height):
        for width_iter in range(watermark_width):

            sub_image = get_sub_image(colored_image, block_size, height_iter, width_iter)
            sub_image_dct = count_image_dct(sub_image)

            if watermark[height_iter, width_iter] == 0:
                if sub_image_dct[3, 3] > sub_image_dct[4, 4]:
                    temp = sub_image_dct[3, 3]
                    sub_image_dct[3, 3] = sub_image_dct[4, 4]
                    sub_image_dct[4, 4] = temp
            else:
                if sub_image_dct[3, 3] < sub_image_dct[4, 4]:
                    temp = sub_image_dct[3, 3]
                    sub_image_dct[3, 3] = sub_image_dct[4, 4]
                    sub_image_dct[4, 4] = temp

            assign_block_to_image(image_wm, block_size, height_iter, width_iter, inverse_dct_to_image(sub_image_dct))

    return image_wm.astype('uint8')


def extract(rgb_image):
    red_image = rgb_image[:, :, 0]
    green_image = rgb_image[:, :, 1]
    blue_image = rgb_image[:, :, 2]

    red_layer_watermark = extract_from_one_layer(red_image)
    green_layer_watermark = extract_from_one_layer(green_image)
    blue_layer_watermark = extract_from_one_layer(blue_image)

    return merge_rgb_image(red_layer_watermark, green_layer_watermark, blue_layer_watermark)


def extract_from_one_layer(coloured_image):
    image_height, image_width = coloured_image.shape

    watermark_height, watermark_width = image_height // block_size, image_width // block_size
    watermark = get_matrix_of_zeros((image_height, image_width))

    for height_iter in range(watermark_height):
        for width_iter in range(watermark_width):

            sub_image_wm = get_sub_image(coloured_image, block_size, height_iter, width_iter).astype('float32')
            sub_image_wm_dct = count_image_dct(sub_image_wm)

            if sub_image_wm_dct[3, 3] < sub_image_wm_dct[4, 4]:
                watermark[height_iter, width_iter] = 0
            else:
                watermark[height_iter, width_iter] = 1

    return watermark[0: watermark_height, 0: watermark_width]


block_size = 8

prepare_window()
image = read_image("images/image.jpg")
print_plot("Image", image)
image = standardize_image_to_blocks(image, block_size)
watermark_to_add = read_grey_image("images/watermark.png")
watermarked_image = add_watermark_to_image(image, watermark_to_add)
print_plot("Watermarked image", watermarked_image / 255)
extracted_watermark = extract(watermarked_image)
print_plot("Extracted watermark", extracted_watermark)
wait_for_key()
