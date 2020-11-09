from global_methods import prepare_window, read_image, print_plot, wait_for_key


image = read_image("images/image.jpg")
# watermark_method = 'DCT'
watermark_method = 'LSBMR'

def _main(_watermark_method, _image):
    image = _image
    watermark_method = _watermark_method

    prepare_window()
    print_plot("Image", image)

    if watermark_method == 'DCT':
        from .dct_watermark import embed, is_watermarked, __extract
        watermarked_image = embed(image)
        print_plot("Watermarked image", watermarked_image / 255)
        is_image_watermarked = is_watermarked(watermarked_image)
        print('watermarked image: ' + str(is_image_watermarked))
        is_image_watermarked = is_watermarked(image)
        print('original image: ' + str(is_image_watermarked))
        # extracted_watermark = __extract(watermarked_image)
        # print_plot("Extracted watermark", extracted_watermark)
        wait_for_key()
        return watermarked_image;

    elif watermark_method == 'LSBMR':
        from .lsbmr_watermark import embed, is_watermarked, __extract
        watermarked_image = embed(image)
        print_plot("Watermarked image", watermarked_image / 255)
        is_image_watermarked = is_watermarked(watermarked_image)
        print('watermarked image: ' + str(is_image_watermarked))
        is_image_watermarked = is_watermarked(image)
        print('original image: ' + str(is_image_watermarked))
        # extracted_watermark = __extract(watermarked_image)
        # print_plot("Extracted watermark", extracted_watermark)
        wait_for_key()
        return watermarked_image;



