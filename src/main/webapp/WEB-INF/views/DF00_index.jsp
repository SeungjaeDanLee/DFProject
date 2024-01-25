<!DOCTYPE html>
<html lang="en">
    <head>
        <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
        <meta name="description" content="" />
        <meta name="author" content="" />
        <title>DogFit</title>


    </head>
    <body>
    <jsp:include page="/resources/layouts/DF00_layouts/DF00_generalNav.jsp"></jsp:include>
        <!-- Header-->
        <div id="carouselExampleInterval" class="carousel slide" data-bs-ride="carousel">
            <div class="carousel-inner">
                <div class="carousel-item active" data-bs-interval="5000">
                    <header class="bg-dark py-5">
                        <div class="container px-4 px-lg-5 my-5">
                            <div class="text-center text-white">
                                <h1 class="display-4 fw-bolder">Dog in your style</h1>
                                <hr>
                                <p class="lead fw-normal text-white-50 mb-0">당신의 반려견은 어떤 모습인가요</p>
                            </div>
                        </div>
                    </header>
                </div>
                <div class="carousel-item" data-bs-interval="5000">
                    <header class="bg-primary py-5">
                        <div class="container px-4 px-lg-5 my-5">
                            <div class="text-center text-white">
                                <h1 class="display-4 fw-bolder">Dog in your life</h1>
                                <hr>
                                <p class="lead fw-normal text-white-50 mb-0">당신은 어떤 반려견을 키우고 있나요??</p>
                            </div>
                        </div>
                    </header>
                </div>
                <div class="carousel-item" data-bs-interval="5000">
                    <header class="bg-secondary py-5">
                        <div class="container px-4 px-lg-5 my-5">
                            <div class="text-center text-white">
                                <h1 class="display-4 fw-bolder">Dog for your style</h1>
                                <hr>
                                <p class="lead fw-normal text-white-50 mb-0">반려견은 당신을 닮았나요??</p>
                            </div>
                        </div>
                    </header>
                </div>
            </div>
            <button class="carousel-control-prev" type="button" data-bs-target="#carouselExampleInterval" data-bs-slide="prev">
                <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                <span class="visually-hidden">Previous</span>
            </button>
            <button class="carousel-control-next" type="button" data-bs-target="#carouselExampleInterval" data-bs-slide="next">
                <span class="carousel-control-next-icon" aria-hidden="true"></span>
                <span class="visually-hidden">Next</span>
            </button>
        </div>



        <!-- Section-->
        <section class="py-5">
            <div class="container px-4 px-lg-5 mt-5">
                <div class="row gx-4 gx-lg-5 row-cols-2 row-cols-md-3 row-cols-xl-4 justify-content-center">
                    <div class="col mb-5">
                        <div class="card h-100">
                            <!-- Product image-->
                            <img class="card-img-top" src="https://dummyimage.com/450x300/dee2e6/6c757d.jpg" alt="..." />
                            <!-- Product details-->
                            <div class="card-body p-4">
                                <div class="text-center">
<%--                                    <!-- Product name-->--%>
<%--                                    <h5 class="fw-bolder">Fancy Product</h5>--%>
<%--                                    <!-- Product price-->--%>
<%--                                    $40.00 - $80.00--%>
                                </div>
                            </div>
<%--                            <!-- Product actions-->--%>
<%--                            <div class="card-footer p-4 pt-0 border-top-0 bg-transparent">--%>
<%--                                <div class="text-center"><a class="btn btn-outline-dark mt-auto" href="#">View options</a></div>--%>
<%--                            </div>--%>
                        </div>
                    </div>
                    <div class="col mb-5">
                        <div class="card h-100">
                            <!-- Sale badge-->
                            <div class="badge bg-dark text-white position-absolute" style="top: 0.5rem; right: 0.5rem">Sale</div>
                            <!-- Product image-->
                            <img class="card-img-top" src="https://dummyimage.com/450x300/dee2e6/6c757d.jpg" alt="..." />
                            <!-- Product details-->
                            <div class="card-body p-4">
                                <div class="text-center">
                                    <!-- Product name-->
                                    <h5 class="fw-bolder">Special Item</h5>
                                    <!-- Product reviews-->
                                    <div class="d-flex justify-content-center small text-warning mb-2">
                                        <div class="bi-star-fill"></div>
                                        <div class="bi-star-fill"></div>
                                        <div class="bi-star-fill"></div>
                                        <div class="bi-star-fill"></div>
                                        <div class="bi-star-fill"></div>
                                    </div>
                                    <!-- Product price-->
                                    <span class="text-muted text-decoration-line-through">$20.00</span>
                                    $18.00
                                </div>
                            </div>
                            <!-- Product actions-->
                            <div class="card-footer p-4 pt-0 border-top-0 bg-transparent">
                                <div class="text-center"><a class="btn btn-outline-dark mt-auto" href="#">Add to cart</a></div>
                            </div>
                        </div>
                    </div>

                    
                </div>
            </div>
        </section>

        <jsp:include page="/resources/layouts/DF00_layouts/DF00_generalFooter.jsp"></jsp:include>

    </body>
</html>