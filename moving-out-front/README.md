# moving-out-front

## Project setup
```
npm install
```

### Compiles and hot-reloads for development
```
npm run serve
```

### Compiles and minifies for production
```
npm run build
```

### Run your unit tests
```
npm run test:unit
```

### Lints and fixes files
```
npm run lint
```

### Customize configuration
See [Configuration Reference](https://cli.vuejs.org/config/).


### Json-server 띄우기
```
json-server --watch db.json
```

* db.json 파일에 아래와 같은 형식으로 mock server 구성 
```json
{
  "data": [
    {
      "id": "1",
      "message": "axios-json-server-test1"
    },
    {
      "id": "2",
      "message": "axios-json-server-test2"
    }
  ]
}
```