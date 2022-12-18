FROM node:16.16.0

WORKDIR /frontend
COPY . /frontend

RUN npm install package.json
# RUN npm i -D esbuild-wasm
# RUN npm install react-router-dom

COPY . ./

CMD ["npm", "run", "build"]
