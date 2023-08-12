#!/usr/bin/env python3
from http.server import HTTPServer, SimpleHTTPRequestHandler, test
import sys

class CORSRequestHandler (SimpleHTTPRequestHandler):

    def do_OPTIONS(self):
        self.send_response(200, "ok")
        self.end_headers()

    def do_POST(self):
        self.send_response(200, "ok")
        self.send_header('Content-Type', 'application/json')
        content_len = int(self.headers.get('Content-Length'))
        post_body = self.rfile.read(content_len)
        print("Got from client - " + post_body.decode())
        self.end_headers()
        if "20406" in post_body.decode():
            self.wfile.write(b'{"ok":1,"coursesMust":[],"coursesDepen":[],"creditsReq":[]}')
        else:
            self.wfile.write(b'{"ok":0,"coursesMust":[20406,20407],"coursesDepen":[[20406,20416]],"creditsReq":[{"creditType":"Math","neededCredits":80,"currentCredits":74}]}')


    def do_GET(self):
        print(self.path)
        if self.path == '/api/course?planId=1':
            self.send_response(200, 'ok')
            self.end_headers()
            self.wfile.write(open('./api/courseGroup/1', 'rb').read())
        else:
            SimpleHTTPRequestHandler.do_GET(self)

    def end_headers (self):
        self.send_header('Access-Control-Allow-Origin', '*')
        self.send_header('Access-Control-Allow-Methods',"GET, PUT, POST, DELETE, OPTIONS")
        self.send_header('Access-Control-Allow-Headers', 'Content-Type')
        SimpleHTTPRequestHandler.end_headers(self)

if __name__ == '__main__':
    test(CORSRequestHandler, HTTPServer, port=int(sys.argv[1]) if len(sys.argv) > 1 else 10000)
