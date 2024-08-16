from flask import Flask, stream_with_context, Response, render_template
from camera import video_capture, start_camera


app = Flask(__name__)

@app.route('/')
def index():
    'index'


@app.route('/api')
def api():
    return 'api'

@app.route('/api/footage')
def footage():
    cam = start_camera()
    return Response(video_capture(cam),
                    mimetype='multipart/x-mixed-replace; boundary=frame')


if __name__ == "__main__":
    app.run(debug=True, port=3000)