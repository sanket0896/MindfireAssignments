function createHTML(htmlStr) {
    var tempContainer = document.createElement('div');
    htmlStr = htmlStr.trim();
    tempContainer.innerHTML = htmlStr;
    var htmlNode = tempContainer.firstChild;    
    return htmlNode;
  }
  
  function generateElementNode(elementId,data) {
    let domElementStr,domElement;
    switch (elementId) {
      case "friends-list": domElementStr = `<li class="col-6 col-sm-3 col-lg-2 mr-0 list-inline-item">
                                              <img src="" alt="" class="img-75" data-desc="image">
                                              <p id="text class="text-justify" data-desc="text"></p>
                                            </li>`;
                          domElement = createHTML(domElementStr);
                          domElement.querySelectorAll("[data-desc = image]")[0].src = data.imageURL;
                          // "https://picsum.photos/200?random";
                          domElement.querySelectorAll("[data-desc = text]")[0].innerHTML = data.name;
                          break;
      case "likes-list": domElementStr = `<li class="col-6 col-sm-3 col-lg-2 mr-0 list-inline-item">
                                            <img src="" alt="" class="img-75" data-desc="image">
                                            <p id="text class="text-justify" data-desc="text"></p>
                                          </li>`;
                        domElement = createHTML(domElementStr);
                        domElement.querySelectorAll("[data-desc = image]")[0].src = data.imageURL;
                        // "https://picsum.photos/200?random";
                        domElement.querySelectorAll("[data-desc = text]")[0].innerHTML = data.name;
                        break;
      case "interests-list": domElementStr = `<li class="col-6 col-sm-3 col-lg-2 mr-0 list-inline-item">
                                                <img src="" alt="" class="img-75" data-desc="image">
                                                <p id="text class="text-justify" data-desc="text"></p>
                                              </li>`;
                            domElement = createHTML(domElementStr);
                            domElement.querySelectorAll("[data-desc = image]")[0].src = data.imageURL;
                            // "https://picsum.photos/200?random";
                            domElement.querySelectorAll("[data-desc = text]")[0].innerHTML = data.name;
                            break;
  default:
      break;
    }
    return domElement;
  }