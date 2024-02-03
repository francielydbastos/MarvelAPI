# API to consume character data from the Marvel API

This API allows the user to get all the character IDs stored in the Marvel API database.
We can also access the info of a specific character, with the option to translate that character description to any language usig the ISO-639-1 code.

## Available endpoints:
- GET /characters : returns a list of the IDs of all the characters in the Marvel API database. Cacheable and runs at startup to make process faster.\
  { id1, id2, id3, ...}
- GET /characters/{characterId}?language={languageCode : returns information about a specific character. If a language code is provided, the character's description will be translated to that language.\
  {
  "id": ,
  "name": "",
  "description": "",
  "thumbnail": {
  "path": "",
  "extension": ""
  }

## Swagger Documentation
- Full and interactive documentation can be accessed through http://localhost:8080/swagger-ui.html