import cv2 as cv
import numpy as np

def start_camera():
    cam = cv.VideoCapture(0)
    return cam

def video_capture(cam):

    while True:
        ret, frame = cam.read()
        if not ret:
            print('cannot capture frame')
            break
        jpg = cv.imencode('.jpg', frame)[1]

        yield (b'--frame\r\n'
                   b'Content-Type: image/jpeg\r\n\r\n' + jpg.tobytes() + b'\r\n')

def stop_camera(cam):
    cam.release()