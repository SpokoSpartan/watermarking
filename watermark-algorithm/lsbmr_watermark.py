from global_methods import read_grey_image, find_shape_similarities, print_plot
from transformation_methods import resize_image, prepare_binary_image, copy_image, to_array

channel = 2


def __func(x1, x2):
    return (x1 // 2 + x2) % 2


def __embed(rgb_image, watermark):
    height, width = rgb_image.shape[:2]
    watermark = resize_image(watermark, (width, height))

    image_c = rgb_image[:, :, channel].flatten()
    watermark = prepare_binary_image(watermark).flatten()

    image_wm = copy_image(rgb_image)
    image_wm_c = image_wm[:, :, channel].flatten()

    # after embedding, w_i = LSB(y_i); w_i+1 = __func(y_i, y_i+1)
    for i in range(0, height * width - 1, 2):
        if watermark[i] == image_c[i] % 2:
            if watermark[i + 1] != __func(image_c[i], image_c[i + 1]):
                image_wm_c[i + 1] = image_c[i + 1] + 1
            else:
                image_wm_c[i + 1] = image_c[i + 1]
            image_wm_c[i] = image_c[i]
        else:
            if watermark[i + 1] == __func(image_c[i] - 1, image_c[i + 1]):
                image_wm_c[i] = image_c[i] - 1
            else:
                image_wm_c[i] = image_c[i] + 1
            image_wm_c[i + 1] = image_c[i + 1]

    image_wm[:, :, channel] = image_wm_c.reshape((height, width))
    return image_wm


def __extract(rgb_image):
    image_wm_c = rgb_image[:, :, channel].flatten()

    extracted_watermark = []
    height, width = rgb_image.shape[:2]
    for i in range(0, height * width - 1, 2):
        w1 = image_wm_c[i] % 2
        w2 = __func(image_wm_c[i], image_wm_c[i + 1])
        extracted_watermark.append(w1)
        extracted_watermark.append(w2)

    return (to_array(extracted_watermark).reshape((height, width)) * 255).astype('uint8')


def embed(image):
    watermark_to_add = read_grey_image("images/watermark.png")
    return __embed(image, watermark_to_add)


def is_watermarked(watermarked_image):
    extracted_watermark = __extract(watermarked_image)
    watermark = read_grey_image("images/watermark.png")
    similarity = find_shape_similarities(extracted_watermark, watermark)
    return similarity[0, 0] > 0.95


def watermark_level(watermarked_image):
    extracted_watermark = __extract(watermarked_image)
    watermark = read_grey_image("images/watermark.png")
    similarity = find_shape_similarities(extracted_watermark, watermark)
    return int(similarity[0, 0] * 100)
