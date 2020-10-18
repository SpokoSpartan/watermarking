import cv2

PRINT_PLOTS = 1


def read_image(name):
    return cv2.imread(name)


def prepare_window():
    if PRINT_PLOTS:
        cv2.namedWindow("Watermarking", cv2.WINDOW_AUTOSIZE)


def print_plot(plot_name, data):
    if PRINT_PLOTS:
        cv2.imshow(plot_name, data)
