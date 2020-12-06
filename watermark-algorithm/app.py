from flask import Flask, request, jsonify
from main import add_watermark, get_watermark_level
from global_image import get_image, upload_image

app = Flask(__name__)
app.config["DEBUG"] = True


@app.route("/algorithm", methods=["POST"])
def algorithm():
    json_response = request.json
    for key, value in json_response.items():
        if key == 'algorithm': alg = value[0]
        if key == 'url': url = value[0]
    filename, image = get_image(url)
    watermarked_image = add_watermark(alg, image)
    image_url = upload_image(watermarked_image, filename)
    return image_url


@app.route("/watermark-level", methods=["POST"])
def watermark_level():
    url = request.args.get("url")
    filename, image = get_image(url)
    return str(get_watermark_level(image))


@app.errorhandler(404)
def page_not_found(e):
    return "<h1>404</h1><p>The resource could not be found.</p>", 404


if __name__ == "__main__":
    app.run(debug=True)
