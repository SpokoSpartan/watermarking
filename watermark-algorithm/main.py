from global_methods import print_plot, TESTING_ALGORITHM, wait_for_key


def get_watermark_level(image):
    from dct_watermark import watermark_level
    dct_wat_lev = watermark_level(image)
    from lsbmr_watermark import watermark_level
    lsbmr_watermark_level = watermark_level(image)
    if dct_wat_lev > lsbmr_watermark_level:
        return dct_wat_lev
    return lsbmr_watermark_level



def add_watermark(watermark_method, image):
    if watermark_method == 'DCT':
        from dct_watermark import embed, is_watermarked, __extract
        watermarked_image = embed(image)
        if TESTING_ALGORITHM == 0:
            return watermarked_image

        print_plot("Watermarked image", watermarked_image / 255)
        is_image_watermarked = is_watermarked(watermarked_image)
        print('watermarked image: ' + str(is_image_watermarked))
        is_image_watermarked = is_watermarked(image)
        print('original image: ' + str(is_image_watermarked))
        extracted_watermark = __extract(watermarked_image)
        print_plot("Extracted watermark", extracted_watermark)

    elif watermark_method == 'LSBMR':
        from lsbmr_watermark import embed, is_watermarked, __extract
        watermarked_image = embed(image)
        if TESTING_ALGORITHM == 0:
            return watermarked_image

        print_plot("Watermarked image", watermarked_image / 255)
        is_image_watermarked = is_watermarked(watermarked_image)
        print('watermarked image: ' + str(is_image_watermarked))
        is_image_watermarked = is_watermarked(image)
        print('original image: ' + str(is_image_watermarked))
        extracted_watermark = __extract(watermarked_image)
        print_plot("Extracted watermark", extracted_watermark)

    wait_for_key()
