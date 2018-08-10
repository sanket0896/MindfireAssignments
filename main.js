function generateElementNode(elementId,domElementStr,data) {
  domElement = createHTML(domElementStr);
  switch (elementId) {
    case "friends-list":
    case "likes-list":
    case "interests-list":
      domElement.querySelectorAll("[data-desc = image]")[0].src = "https://picsum.photos/200?random";
      domElement.querySelectorAll("[data-desc = text]")[0].innerHTML = "Friend "+data;
      break;
    case "bio-text":
      domElement.querySelectorAll("[data-desc = text]")[0].innerHTML = "kajsndkasndjk ajdsnkandsa ksdjas dkasjd kdansd cjhsa j acj."+data;
      break;
    default:
      break;
  }
  return domElement;
}

function populateDataInElement(elementId,domElementStr,data) {
  dataLength = data.length;
  // dataLength = 6;//used for testing
  for (let index = 0; index < dataLength; index++) {
    domElement = generateElementNode(elementId,domElementStr,index)
    document.getElementById(elementId).appendChild(domElement);  
  }
}
populateDataInElement("friends-list",friendElementStr,[{},{},{},{},{},{}]);
populateDataInElement("likes-list",friendElementStr,[{},{},{},{},{},{}]);
populateDataInElement("interests-list",friendElementStr,[{},{},{},{},{},{}]);
populateDataInElement("bio-text",bioElementStr,[{}]);