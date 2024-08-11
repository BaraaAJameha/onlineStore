const createFooter = () => {
    let footer = document.querySelector('footer');

    footer.innerHTML = `
    <div class="footer-content">
        <img src="C:/xampp/htdocs/public/img/footerimg.jpg" class="logo" alt="">
        <div class="footer-ul-container">
        <ul class="category">
        <li class="category-title">main menu</li>
        <li><a href="#" class="footer-link">Mercedes</a></li>
        <li><a href="#" class="footer-link">Ford</a></li>
        <li><a href="#" class="footer-link">Nissan</a></li>
        <li><a href="#" class="footer-link">Tucker</a></li>
        <li><a href="#" class="footer-link">Shelby</a></li>
        <li><a href="#" class="footer-link">Porsche</a></li>
        <li><a href="#" class="footer-link">Toyota</a></li>
        <li><a href="#" class="footer-link">Mazda</a></li>
        <li><a href="#" class="footer-link">Chevrolet</a></li>
        <li><a href="#" class="footer-link">Hyundai</a></li>
    </ul>
    <ul class="category">
    <li class="category-title">Transportation</li>
    <li><a href="#" class="footer-link">Planes</a></li>
    <li><a href="#" class="footer-link">Ships</a></li>
    <li><a href="#" class="footer-link">Trucks</a></li>
    <li><a href="#" class="footer-link">Buses</a></li>
    <li><a href="#" class="footer-link">Motorcycles</a></li>
    <li><a href="#" class="footer-link">Vintage Cars</a></li>
    <li><a href="#" class="footer-link">Trains</a></li>
    <li><a href="#" class="footer-link">Bus</a></li>
    <li><a href="#" class="footer-link">Van</a></li>
    <li><a href="#" class="footer-link">Boat</a></li>
    </ul>
        </div>
    </div>
    <p class="footer-title">about company</p>
    <p class="info">Lorem ipsum, dolor sit amet consectetur adipisicing elit. Repellat tempore ad suscipit, eos eius quisquam sed optio nisi quaerat fugiat ratione et vero maxime praesentium, architecto minima reiciendis iste quo deserunt assumenda alias ducimus. Ullam odit maxime id voluptates rerum tenetur corporis laboriosam! Cum error ipsum laborum tempore in rerum necessitatibus nostrum nobis modi! Debitis adipisci illum nemo aperiam sed, et accusamus ut officiis. Laborum illo exercitationem quo culpa reprehenderit excepturi distinctio tempore cupiditate praesentium nisi ut iusto, assumenda perferendis facilis voluptas autem fuga sunt ab debitis voluptatum harum eum. Asperiores, natus! Est deserunt incidunt quasi placeat omnis, itaque harum?</p>
    <p class="info">support emails - help@classic.com, customersupport@classic.com</p>
    <p class="info">telephone - 180 00 66 001, 180 00 66 002</p>
    
    <p class="footer-credit">Classic Cars, Best cars online store</p>
    `;
}

createFooter();