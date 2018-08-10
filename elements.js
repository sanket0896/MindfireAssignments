function createHTML(htmlStr) {
    var tempContainer = document.createElement('div');
    htmlStr = htmlStr.trim();
    tempContainer.innerHTML = htmlStr;
    var htmlNode = tempContainer.firstChild;
    console.log(htmlNode);
    
    return htmlNode;
}

let friendElementStr = `<li class="col-6 col-sm-3 col-lg-2 mr-0 list-inline-item">
                            <img src="" alt="" class="img-75" data-desc="image">
                            <p id="text class="text-justify" data-desc="text"></p>
                        </li>`;

let likeElementStr = `<li class="col-6 col-sm-3 col-lg-2 mr-0 list-inline-item">
                            <img src="" alt="" class="img-75" data-desc="image">
                            <p id="text class="text-justify" data-desc="text"></p>
                        </li>`;

let interestElementStr = `<li class="col-6 col-sm-3 col-lg-2 mr-0 list-inline-item">
                            <img src="" alt="" class="img-75" data-desc="image">
                            <p id="text class="text-justify" data-desc="text"></p>
                        </li>`;
                        
let bioElementStr = `<div>
                        <p data-desc="text"></p>
                    </div>`;  