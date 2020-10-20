import cv2

PRINT_PLOTS = 1


def read_image(name):
    return cv2.imread(name)


def read_grey_image(name):
    return cv2.imread(name, 0)


def prepare_window():
    if PRINT_PLOTS:
        cv2.namedWindow("Watermarking", cv2.WINDOW_AUTOSIZE)


def print_plot(plot_name, data):
    if PRINT_PLOTS:
        cv2.imshow(plot_name, data)


def wait_for_key():
    if PRINT_PLOTS:
        cv2.waitKey(0)


def __normalize_shape(shape):
    return shape if shape[0] > shape[1] else (shape[1], shape[0])


def get_shape_divided_by(shape, divider):
    return int(shape[0] / divider), int(shape[1] / divider)


def reverse_shape(shape):
    return shape[1], shape[0]
