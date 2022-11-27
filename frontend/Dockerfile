FROM node:16.16.0

WORKDIR /frontend
COPY . /frontend

RUN npm install package.json

COPY . ./

CMD ["yarn", "run", "build"]
