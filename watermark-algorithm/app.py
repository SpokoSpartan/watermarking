from tkinter import Image

from flask import Flask, request, jsonify
from global_methods import print_plot
from main import _main
from download_image import get_image

app = Flask(__name__)
app.config["DEBUG"] = True


@app.route("/algorithm", methods=["POST"])
def algorithm():

    jsonResponse = request.json
    for key, value in jsonResponse.items():
        print(key, value[0])
        if (key =='algorithm'): alg = value[0]
        if (key == 'url'): url = value[0]

    img = get_image(url)

    watermarked_image = _main(alg, img)

    response = jsonify("WatermarkedImg : " + url)
    response.status_code = 202  # Provides a response status code of 202 which is "Accepted"

    return response;


@app.errorhandler(404)
def page_not_found(e):
    return "<h1>404</h1><p>The resource could not be found.</p>", 404



if __name__ == "__main__":
    app.run(debug=True)
