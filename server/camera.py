import cv2 as cv

def video_capture():
    vid = cv.VideoCapture(0)

    while True:
        ret, frame = vid.read()
        if not ret:
            print('cannot capture frame')
            break
        cv.imshow('frame', frame)
        if cv.waitKey(1) == ord('q'):
            break
    vid.release()
    cv.destroyAllWindows()

video_capture()