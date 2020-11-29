from global_methods import read_image, TESTING_ALGORITHM
from main import add_watermark

watermark_method = 'DCT'
# watermark_method = 'LSBMR'


image = read_image("images/image.jpg")
add_watermark(watermark_method, image)
