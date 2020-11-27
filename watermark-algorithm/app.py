from tkinter import Image

from flask import Flask, request, jsonify
from global_methods import print_plot
from main import _main
from download_image import get_image

app = Flask(__name__)
app.config["DEBUG"] = True

#url = https://res.cloudinary.com/demo/image/upload/sample.jpg?fbclid=IwAR0mghGsRqq_Bh2Dk-BmSxuZIGiu1NHfvuvfgADbYBxTZjfO8woMkHnr_EM

@app.route("/algorithm", methods=["POST"])
def algorithm():
    alg = request.args.get("algorithm")
    url = request.args.get("url")
    img = get_image(url)
    print(img)
    #print_plot("Image", img)
    response = jsonify(url)
    response.status_code = 202  # Provides a response status code of 202 which is "Accepted"
    watermarked_image = _main(alg, img)

    return response;


@app.errorhandler(404)
def page_not_found(e):
    return "<h1>404</h1><p>The resource could not be found.</p>", 404



if __name__ == "__main__":
    app.run(debug=True)
