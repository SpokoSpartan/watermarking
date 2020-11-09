from tkinter import Image

from flask import Flask, request, jsonify
from global_methods import print_plot
from main import _main
from download_image import get_image

app = Flask(__name__)
app.config["DEBUG"] = True

@app.route("/algorithm", methods=["POST"])
def algorithm():
    alg = request.form.get("algorithm")
    url = request.form.get("url")
    img = get_image(url)

    print_plot("Image", img)

    #watermarked_image = _main(alg, img)

    return jsonify(url);


@app.errorhandler(404)
def page_not_found(e):
    return "<h1>404</h1><p>The resource could not be found.</p>", 404


if __name__ == "__main__":
    app.run(debug=True)
