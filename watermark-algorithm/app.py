from flask import Flask, request, jsonify
from main import add_watermark
from global_image import get_image, upload_image

app = Flask(__name__)
app.config["DEBUG"] = True


@app.route("/algorithm", methods=["POST"])
def algorithm():
    alg = request.args.get("algorithm")
    url = request.args.get("url")
    filename, image = get_image(url)
    watermarked_image = add_watermark(alg, image)
    image_url = upload_image(watermarked_image, filename)
    response = jsonify(image_url)
    response.status_code = 200  # Provides a response status code of 200 which is "Ok"
    return response


@app.errorhandler(404)
def page_not_found(e):
    return "<h1>404</h1><p>The resource could not be found.</p>", 404


if __name__ == "__main__":
    app.run(debug=True)
