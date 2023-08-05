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
            self.wfile.write(b'{"ok":1,"courses-must":[]}')
        else:
            self.wfile.write(b'{"ok":0,"courses-must":[20406,20407],"courses-depen":[]}')


    def end_headers (self):
        self.send_header('Access-Control-Allow-Origin', '*')
        self.send_header('Access-Control-Allow-Methods',"GET, PUT, POST, DELETE, OPTIONS")
        self.send_header('Access-Control-Allow-Headers', 'Content-Type')
        SimpleHTTPRequestHandler.end_headers(self)

if __name__ == '__main__':
    test(CORSRequestHandler, HTTPServer, port=int(sys.argv[1]) if len(sys.argv) > 1 else 10000)
