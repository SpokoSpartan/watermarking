from global_methods import get_shape_divided_by, \
    reverse_shape, read_grey_image, find_shape_similarities
from transformation_methods import count_image_dct, inverse_dct_to_image, resize_image, get_sub_image, \
    assign_block_to_image, get_matrix_of_zeros, \
    prepare_binary_image, copy_image, standardize_image_to_blocks

channel = 2
block_size = 8


def __embed(rgb_image, watermark):
    image_c = rgb_image[:, :, channel]

    # make sure that watermark is 8 time smaller than image
    watermark_shape = get_shape_divided_by(image_c.shape, block_size)
    watermark_shape = reverse_shape(watermark_shape)
    watermark = resize_image(watermark, watermark_shape)

    watermark = prepare_binary_image(watermark)

    colored_image = image_c.astype('float32')

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

    rgb_image[:, :, channel] = image_wm
    return rgb_image.astype('uint8')


def __extract(rgb_image):
    image_c = rgb_image[:, :, channel]
    image_height, image_width = image_c.shape

    watermark_height, watermark_width = image_height // block_size, image_width // block_size
    watermark = get_matrix_of_zeros((image_height, image_width))

    for height_iter in range(watermark_height):
        for width_iter in range(watermark_width):

            sub_image_wm = get_sub_image(image_c, block_size, height_iter, width_iter).astype('float32')
            sub_image_wm_dct = count_image_dct(sub_image_wm)

            if sub_image_wm_dct[3, 3] < sub_image_wm_dct[4, 4]:
                watermark[height_iter, width_iter] = 0
            else:
                watermark[height_iter, width_iter] = 1

    return watermark[0: watermark_height, 0: watermark_width]


def embed(image):
    image = standardize_image_to_blocks(image, block_size)
    watermark_to_add = read_grey_image("images/watermark.png")
    image_cp = copy_image(image)
    return __embed(image_cp, watermark_to_add)


def is_watermarked(watermarked_image):
    extracted_watermark = __extract(watermarked_image)
    watermark = read_grey_image("images/watermark.png")
    similarity = find_shape_similarities(extracted_watermark, watermark)
    return similarity[0, 0] > 0.90


def watermark_level(watermarked_image):
    extracted_watermark = __extract(watermarked_image)
    watermark = read_grey_image("images/watermark.png")
    similarity = find_shape_similarities(extracted_watermark, watermark)
    return int(similarity[0, 0] * 100)
