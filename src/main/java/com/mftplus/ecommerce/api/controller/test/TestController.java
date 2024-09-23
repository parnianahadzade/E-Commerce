package com.mftplus.ecommerce.api.controller.test;

import com.mftplus.ecommerce.exception.DuplicateException;
import com.mftplus.ecommerce.exception.EmailFailureException;
import com.mftplus.ecommerce.model.entity.*;
import com.mftplus.ecommerce.model.entity.enums.Size;
import com.mftplus.ecommerce.repository.ImageRepository;
import com.mftplus.ecommerce.repository.UserRepository;
import com.mftplus.ecommerce.service.impl.*;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("${apiPrefix}/test")
@Slf4j
@CrossOrigin
public class TestController {

    private final UserRepository userRepository;

    private final RoleServiceImpl roleService;

    private final PersonServiceImpl personService;

    private final BrandServiceImpl brandService;

    private final CategoryServiceImpl categoryService;

    private final ColorServiceImpl colorService;

    private final ImageRepository imageRepository;

    private final ProductServiceImpl productService;

    private final InventoryServiceImpl inventoryService;


    public TestController(UserRepository userRepository, RoleServiceImpl roleService, PersonServiceImpl personService, BrandServiceImpl brandService, CategoryServiceImpl categoryService, ColorServiceImpl colorService, ImageRepository imageRepository, ProductServiceImpl productService, InventoryServiceImpl inventoryService) {
        this.userRepository = userRepository;
        this.roleService = roleService;
        this.personService = personService;
        this.brandService = brandService;
        this.categoryService = categoryService;
        this.colorService = colorService;
        this.imageRepository = imageRepository;
        this.productService = productService;
        this.inventoryService = inventoryService;
    }

    @Transactional
    @GetMapping
    public void test() throws DuplicateException, EmailFailureException {

        //person
        Person adminPerson = new Person();
        adminPerson.setFirstName("ادمین");
        adminPerson.setLastName("ادمین");
        adminPerson.setPostalCode("1489633937");
        adminPerson.setPhoneNumber("09123896543");
        adminPerson.setAddressLine("دهکده المپیک، زیبا دششت، خ لاله");
        personService.save(adminPerson);

        //role
        Role admin = new Role();
        admin.setName("admin");
        roleService.save(admin);

        Role user = new Role();
        user.setName("user");
        roleService.save(user);

        List<Role> roles = new ArrayList<>();
        roles.add(admin);
        roles.add(user);

        //user
        User adminUser = new User();
        adminUser.setUsername("Admin");
        adminUser.setPassword("$2a$10$hBn5gu6cGelJNiE6DDsaBOmZgyumCSzVwrOK/37FWgJ6aLIdZSSI2");
        adminUser.setEmail("admin@email.com");
        adminUser.setEmailVerified(true);
        adminUser.setRoles(roles);
        adminUser.setVersionId(0L);
        adminUser.setPerson(adminPerson);
        adminUser.setIdentified(true);
        userRepository.save(adminUser);

        //brand
        Brand brand = new Brand();
        brand.setName("ال سی وایکیکی");
        brand.setExplanation("این توضیحاات برند است.");
        brandService.save(brand);

        Brand brand1 = new Brand();
        brand1.setName("اچ اند ام");
        brand1.setExplanation("این توضیحاات برند است.");
        brandService.save(brand1);

        Brand brand2 = new Brand();
        brand2.setName("اسمارا");
        brand2.setExplanation("این توضیحاات برند است.");
        brandService.save(brand2);

        //category
        Category digikala = new Category();
        digikala.setName("دیجی کالا");
        categoryService.save(digikala);

        Category hudi = new Category();
        hudi.setName("هودی");
        hudi.setParentCategory(digikala);
        categoryService.save(hudi);

        Category hudiW = new Category();
        hudiW.setName("هودی زنانه");
        hudiW.setParentCategory(hudi);
        categoryService.save(hudiW);

        Category hudiM = new Category();
        hudiM.setName("هودی مردانه");
        hudiM.setParentCategory(hudi);
        categoryService.save(hudiM);

        Category hudiU = new Category();
        hudiU.setName("هودی یونیسکس");
        hudiU.setParentCategory(hudi);
        categoryService.save(hudiU);

        Category baft = new Category();
        baft.setName("بافت");
        baft.setParentCategory(digikala);
        categoryService.save(baft);

        Category baftW = new Category();
        baftW.setName("بافت زنانه");
        baftW.setParentCategory(baft);
        categoryService.save(baftW);

        Category baftM = new Category();
        baftM.setName("بافت مردانه");
        baftM.setParentCategory(baft);
        categoryService.save(baftM);

        Category baftU = new Category();
        baftU.setName("بافت یونیسکس");
        baftU.setParentCategory(baft);
        categoryService.save(baftU);

        Category tShirt = new Category();
        tShirt.setName("تیشرت");
        tShirt.setParentCategory(digikala);
        categoryService.save(tShirt);

        Category tShirtU = new Category();
        tShirtU.setName("تیشرت یونیسکس");
        tShirtU.setParentCategory(tShirt);
        categoryService.save(tShirtU);

        Category blooz = new Category();
        blooz.setName("بلوز");
        blooz.setParentCategory(digikala);
        categoryService.save(blooz);

        Category bloozW = new Category();
        bloozW.setName("بلوز زنانه");
        bloozW.setParentCategory(blooz);
        categoryService.save(bloozW);


        //color
        Color red = new Color();
        red.setName("قرمز");
        red.setHexCode("#ff0030");
        colorService.save(red);

        Color blue = new Color();
        blue.setName("آبی");
        blue.setHexCode("#2850a7");
        colorService.save(blue);

        Color green = new Color();
        green.setName("سبز");
        green.setHexCode("#18af10");
        colorService.save(green);

        Color purple = new Color();
        purple.setName("بنفش");
        purple.setHexCode("#a91dbf");
        colorService.save(purple);

        Color keremi = new Color();
        keremi.setName("کرمی");
        keremi.setHexCode("#d3cfd4");
        colorService.save(keremi);

        Color black = new Color();
        black.setName("مشکی");
        black.setHexCode("#161316");
        colorService.save(black);

        Color white = new Color();
        white.setName("سفید");
        white.setHexCode("#f2eeed");
        colorService.save(white);

        //image
        //1
        Image baftGolGoli = new Image();
        baftGolGoli.setFilePath("/productImages/3b9b403a-1b5f-4d60-8e18-be339b475028_baftGolGoli.webp");
        imageRepository.save(baftGolGoli);

        //2
        Image baftAstinKootah = new Image();
        baftAstinKootah.setFilePath("/productImages/4d615660-fd77-40dc-8c5c-cb1cc4f12316_baftAstinKootah.webp");
        imageRepository.save(baftAstinKootah);

        //3
        Image poliverSefid = new Image();
        poliverSefid.setFilePath("/productImages/5c4ea841-62eb-4015-9d90-de29b3b3b428_poliverSefid.webp");
        imageRepository.save(poliverSefid);

        //4
        Image cuteHudi = new Image();
        cuteHudi.setFilePath("/productImages/6e956077-dab6-40aa-a94c-11c7905660a0_cuteHudi.webp");
        imageRepository.save(cuteHudi);

        //5
        Image tShirtKhersi = new Image();
        tShirtKhersi.setFilePath("/productImages/06cd8cc9-a190-414a-8ad7-e1798c484ffc_tshirtKhersi.webp");
        imageRepository.save(tShirtKhersi);

        //6
        Image whiteCuteHudiWomen = new Image();
        whiteCuteHudiWomen.setFilePath("/productImages/e80c1f95-0be2-41dd-ba87-0bb853aebdd3_whiteCuteHudiWomen.webp");
        imageRepository.save(whiteCuteHudiWomen);

        //7
        Image whiteCuteHudiWomenSub = new Image();
        whiteCuteHudiWomenSub.setFilePath("/productImages/8dbe4315-fbdb-4c85-a669-42dad9e4e1be_witheCuteHudiWomenSub.webp");
        imageRepository.save(whiteCuteHudiWomenSub);

        //8
        Image whiteCuteHudiWomenSub2 = new Image();
        whiteCuteHudiWomenSub2.setFilePath("/productImages/8680a786-7b56-4599-ad4b-a0e8580689d6_whiteCuteHudiWomenSub2.webp");
        imageRepository.save(whiteCuteHudiWomenSub2);

        //9
        Image baftHnM = new Image();
        baftHnM.setFilePath("/productImages/9c82ce6e-2516-4270-9cae-5bd30c30c0af_baftHnM.webp");
        imageRepository.save(baftHnM);

        //10
        Image poliverAbi = new Image();
        poliverAbi.setFilePath("/productImages/9f97a899-5813-4da2-9e87-7606dd4c5ffe_poliverApi.webp");
        imageRepository.save(poliverAbi);

        //11
        Image usagiHudiSub = new Image();
        usagiHudiSub.setFilePath("/productImages/17dd152c-4725-4e48-ba04-45450a350d99_usagiHudiSub.webp");
        imageRepository.save(usagiHudiSub);

        //12
        Image baftMardaneSade = new Image();
        baftMardaneSade.setFilePath("/productImages/43e102ff-ffa8-409b-8ab1-98f448081ad7_baftMardaneSade.webp");
        imageRepository.save(baftMardaneSade);

        //13
        Image baftMardane = new Image();
        baftMardane.setFilePath("/productImages/e0931b7d-4c39-4963-a03c-265887420aa9_baftMardane.webp");
        imageRepository.save(baftMardane);

        //14
        Image booloozZanane = new Image();
        booloozZanane.setFilePath("/productImages/96d441fe-0185-46d1-bbaf-cdeb0b9121c3_booloozZanane.webp");
        imageRepository.save(booloozZanane);

        //15
        Image whiteHudiMen = new Image();
        whiteHudiMen.setFilePath("/productImages/a326df88-b096-4285-9159-70bd732789d9_whiteHudiMen.webp");
        imageRepository.save(whiteHudiMen);

        //16
        Image baftSabz = new Image();
        baftSabz.setFilePath("/productImages/a37a6038-f56b-47d2-8fd0-64b1bd2dcca7_baft.webp");
        imageRepository.save(baftSabz);

        //17
        Image tShirtMeshki = new Image();
        tShirtMeshki.setFilePath("/productImages/dcfdc52f-a56e-4cea-9af4-c54a9854b0f4_116890989.webp");
        imageRepository.save(tShirtMeshki);

        //product
        //code : 1 = baft zanoone
        //code : 2 = hudi
        //code : 3 = tShirt
        //code : 4 = hudiW
        //code : 5 = hudiM
        //code : 6 = baft mardoone
        //code : 7 = blooz zanane

        //1
        Product product1 = new Product();
        List<Category> categories1 = new ArrayList<>();
        categories1.add(digikala);
        categories1.add(baft);
        categories1.add(baftW);

        product1.setCode("1");
        product1.setName("بافت زنانه طرح گلدار");
        product1.setDescription("توضیحات برای لباس مورد نظر، طرح آن و جنس آن.");
        product1.setBrand(brand);
        product1.setCategories(categories1);
        product1.setMainCategory(baftW);
        product1.setMainImage(baftGolGoli);
        product1.setColor(black);
        product1.setPrice(1000000);
        product1.setOffPercent(10);
        product1.setMaterial("پشم");
        product1.setHeight(65);
        product1.setPattern("طرحدار");
        productService.save(product1);

        Inventory inventory1 = new Inventory();
        inventory1.setQuantity(10);
        inventory1.setProduct(product1);
        inventory1.setSize(Size.S);
        inventoryService.save(inventory1);

        Inventory inventory2 = new Inventory();
        inventory2.setQuantity(10);
        inventory2.setProduct(product1);
        inventory2.setSize(Size.XL);
        inventoryService.save(inventory2);


        //2
        Product product2 = new Product();
        List<Category> categories2 = new ArrayList<>();
        categories2.add(digikala);
        categories2.add(baft);
        categories2.add(baftW);

        product2.setCode("1");
        product2.setName("بافت زنانه آستین کوتاه");
        product2.setDescription("توضیحات برای لباس مورد نظر، طرح آن و جنس آن.");
        product2.setBrand(brand);
        product2.setCategories(categories2);
        product2.setMainCategory(baftW);
        product2.setMainImage(baftAstinKootah);
        product2.setColor(blue);
        product2.setPrice(1000000);
        product2.setOffPercent(10);
        product2.setMaterial("پشم");
        product2.setHeight(65);
        product2.setPattern("طرحدار");
        productService.save(product2);

        Inventory inventory3 = new Inventory();
        inventory3.setQuantity(10);
        inventory3.setProduct(product2);
        inventory3.setSize(Size.S);
        inventoryService.save(inventory3);

        Inventory inventory4 = new Inventory();
        inventory4.setQuantity(10);
        inventory4.setProduct(product2);
        inventory4.setSize(Size.XL);
        inventoryService.save(inventory4);


        //3
        Product product3 = new Product();
        List<Category> categories3 = new ArrayList<>();
        categories3.add(digikala);
        categories3.add(baft);
        categories3.add(baftW);

        product3.setCode("1");
        product3.setName("پلیور زنانه سفید");
        product3.setDescription("توضیحات برای لباس مورد نظر، طرح آن و جنس آن.");
        product3.setBrand(brand);
        product3.setCategories(categories3);
        product3.setMainCategory(baftW);
        product3.setMainImage(poliverSefid);
        product3.setColor(white);
        product3.setPrice(1000000);
        product3.setOffPercent(10);
        product3.setMaterial("پشم");
        product3.setHeight(65);
        product3.setPattern("طرحدار");
        productService.save(product3);

        Inventory inventory5 = new Inventory();
        inventory5.setQuantity(10);
        inventory5.setProduct(product3);
        inventory5.setSize(Size.S);
        inventoryService.save(inventory5);

        Inventory inventory6 = new Inventory();
        inventory6.setQuantity(10);
        inventory6.setProduct(product3);
        inventory6.setSize(Size.XL);
        inventoryService.save(inventory6);


        //4
        Product product4 = new Product();
        List<Category> categories4 = new ArrayList<>();
        categories4.add(digikala);
        categories4.add(hudi);
        categories4.add(hudiU);

        product4.setCode("2");
        product4.setName("هودی طرحدار عروسکی");
        product4.setDescription("توضیحات برای لباس مورد نظر، طرح آن و جنس آن.");
        product4.setBrand(brand);
        product4.setCategories(categories4);
        product4.setMainCategory(hudiU);
        product4.setMainImage(cuteHudi);
        product4.setColor(black);
        product4.setPrice(1000000);
        product4.setOffPercent(10);
        product4.setMaterial("پشم");
        product4.setHeight(65);
        product4.setPattern("طرحدار");
        productService.save(product4);

        Inventory inventory7 = new Inventory();
        inventory7.setQuantity(10);
        inventory7.setProduct(product4);
        inventory7.setSize(Size.L);
        inventoryService.save(inventory7);

        Inventory inventory8 = new Inventory();
        inventory8.setQuantity(10);
        inventory8.setProduct(product4);
        inventory8.setSize(Size.XL);
        inventoryService.save(inventory8);


        //5
        Product product5 = new Product();
        List<Category> categories5 = new ArrayList<>();
        categories5.add(digikala);
        categories5.add(tShirt);
        categories5.add(tShirtU);

        product5.setCode("3");
        product5.setName("تیشرت خرسی");
        product5.setDescription("توضیحات برای لباس مورد نظر، طرح آن و جنس آن.");
        product5.setBrand(brand1);
        product5.setCategories(categories5);
        product5.setMainCategory(tShirtU);
        product5.setMainImage(tShirtMeshki);
        product5.setColor(white);
        product5.setPrice(1000000);
        product5.setOffPercent(10);
        product5.setMaterial("پشم");
        product5.setHeight(65);
        product5.setPattern("طرحدار");
        productService.save(product5);

        Inventory inventory9 = new Inventory();
        inventory9.setQuantity(10);
        inventory9.setProduct(product5);
        inventory9.setSize(Size.L);
        inventoryService.save(inventory9);

        Inventory inventory10 = new Inventory();
        inventory10.setQuantity(10);
        inventory10.setProduct(product5);
        inventory10.setSize(Size.XL);
        inventoryService.save(inventory10);



        //6
        Product product6 = new Product();
        List<Category> categories6 = new ArrayList<>();
        categories6.add(digikala);
        categories6.add(hudi);
        categories6.add(hudiW);

        product6.setCode("4");
        product6.setName("هودی زنانه ساده");
        product6.setDescription("توضیحات برای لباس مورد نظر، طرح آن و جنس آن.");
        product6.setBrand(brand1);
        product6.setCategories(categories6);
        product6.setMainCategory(hudiW);
        product6.setMainImage(whiteCuteHudiWomen);
        List<Image> images = new ArrayList<>();
        images.add(whiteCuteHudiWomenSub);
        images.add(whiteCuteHudiWomenSub2);
        product6.setImages(images);
        product6.setColor(white);
        product6.setPrice(1000000);
        product6.setOffPercent(10);
        product6.setMaterial("پشم");
        product6.setHeight(65);
        product6.setPattern("طرحدار");
        productService.save(product6);

        Inventory inventory11 = new Inventory();
        inventory11.setQuantity(10);
        inventory11.setProduct(product6);
        inventory11.setSize(Size.XL2);
        inventoryService.save(inventory11);

        Inventory inventory12 = new Inventory();
        inventory12.setQuantity(10);
        inventory12.setProduct(product6);
        inventory12.setSize(Size.XL);
        inventoryService.save(inventory12);


        //7
        Product product7 = new Product();
        List<Category> categories7 = new ArrayList<>();
        categories7.add(digikala);
        categories7.add(baft);
        categories7.add(baftW);

        product7.setCode("1");
        product7.setName("پلیور زنانه طرحدار");
        product7.setDescription("توضیحات برای لباس مورد نظر، طرح آن و جنس آن.");
        product7.setBrand(brand2);
        product7.setCategories(categories7);
        product7.setMainCategory(baftW);
        product7.setMainImage(baftHnM);
        product7.setColor(white);
        product7.setPrice(1000000);
        product7.setOffPercent(10);
        product7.setMaterial("پشم");
        product7.setHeight(65);
        product7.setPattern("طرحدار");
        productService.save(product7);

        Inventory inventory13 = new Inventory();
        inventory13.setQuantity(10);
        inventory13.setProduct(product7);
        inventory13.setSize(Size.XL2);
        inventoryService.save(inventory13);

        Inventory inventory14 = new Inventory();
        inventory14.setQuantity(10);
        inventory14.setProduct(product7);
        inventory14.setSize(Size.XL);
        inventoryService.save(inventory14);


        //8
        Product product8 = new Product();
        List<Category> categories8 = new ArrayList<>();
        categories8.add(digikala);
        categories8.add(baft);
        categories8.add(baftW);

        product8.setCode("1");
        product8.setName("پلیور زنانه ساده و کوتاه");
        product8.setDescription("توضیحات برای لباس مورد نظر، طرح آن و جنس آن.");
        product8.setBrand(brand2);
        product8.setCategories(categories8);
        product8.setMainCategory(baftW);
        product8.setMainImage(poliverAbi);
        product8.setColor(blue);
        product8.setPrice(1000000);
        product8.setOffPercent(10);
        product8.setMaterial("پشم");
        product8.setHeight(65);
        product8.setPattern("طرحدار");
        productService.save(product8);

        Inventory inventory15 = new Inventory();
        inventory15.setQuantity(10);
        inventory15.setProduct(product8);
        inventory15.setSize(Size.XL2);
        inventoryService.save(inventory15);

        Inventory inventory16 = new Inventory();
        inventory16.setQuantity(10);
        inventory16.setProduct(product8);
        inventory16.setSize(Size.XL);
        inventoryService.save(inventory16);


        //9
        Product product9 = new Product();
        List<Category> categories9 = new ArrayList<>();
        categories9.add(digikala);
        categories9.add(hudi);
        categories9.add(hudiW);

        product9.setCode("2");
        product9.setName("هودی زنانه طرح خرگوش");
        product9.setDescription("توضیحات برای لباس مورد نظر، طرح آن و جنس آن.");
        product9.setBrand(brand2);
        product9.setCategories(categories9);
        product9.setMainCategory(hudiW);
        product9.setMainImage(usagiHudiSub);
        product9.setColor(white);
        product9.setPrice(1000000);
        product9.setOffPercent(10);
        product9.setMaterial("پشم");
        product9.setHeight(65);
        product9.setPattern("طرحدار");
        productService.save(product9);

        Inventory inventory17 = new Inventory();
        inventory17.setQuantity(10);
        inventory17.setProduct(product9);
        inventory17.setSize(Size.XL2);
        inventoryService.save(inventory17);

        Inventory inventory18 = new Inventory();
        inventory18.setQuantity(10);
        inventory18.setProduct(product9);
        inventory18.setSize(Size.XL);
        inventoryService.save(inventory18);


        //10
        Product product10 = new Product();
        List<Category> categories10 = new ArrayList<>();
        categories10.add(digikala);
        categories10.add(baft);
        categories10.add(baftM);

        product10.setCode("6");
        product10.setName("بافت مردانه ساده");
        product10.setDescription("توضیحات برای لباس مورد نظر، طرح آن و جنس آن.");
        product10.setBrand(brand2);
        product10.setCategories(categories10);
        product10.setMainCategory(baftM);
        product10.setMainImage(baftMardaneSade);
        product10.setColor(black);
        product10.setPrice(1000000);
        product10.setOffPercent(10);
        product10.setMaterial("پشم");
        product10.setHeight(65);
        product10.setPattern("طرحدار");
        productService.save(product10);

        Inventory inventory19 = new Inventory();
        inventory19.setQuantity(10);
        inventory19.setProduct(product10);
        inventory19.setSize(Size.XL2);
        inventoryService.save(inventory19);

        Inventory inventory20 = new Inventory();
        inventory20.setQuantity(10);
        inventory20.setProduct(product10);
        inventory20.setSize(Size.XL);
        inventoryService.save(inventory20);



        //11
        Product product11 = new Product();
        List<Category> categories11 = new ArrayList<>();
        categories11.add(digikala);
        categories11.add(baft);
        categories11.add(baftM);

        product11.setCode("6");
        product11.setName("بافت مردانه");
        product11.setDescription("توضیحات برای لباس مورد نظر، طرح آن و جنس آن.");
        product11.setBrand(brand2);
        product11.setCategories(categories11);
        product11.setMainCategory(baftM);
        product11.setMainImage(baftMardane);
        product11.setColor(white);
        product11.setPrice(1000000);
        product11.setOffPercent(10);
        product11.setMaterial("پشم");
        product11.setHeight(65);
        product11.setPattern("طرحدار");
        productService.save(product11);

        Inventory inventory21 = new Inventory();
        inventory21.setQuantity(10);
        inventory21.setProduct(product11);
        inventory21.setSize(Size.XL2);
        inventoryService.save(inventory21);

        Inventory inventory22 = new Inventory();
        inventory22.setQuantity(10);
        inventory22.setProduct(product11);
        inventory22.setSize(Size.XL);
        inventoryService.save(inventory22);


        //12
        Product product12 = new Product();
        List<Category> categories12 = new ArrayList<>();
        categories12.add(digikala);
        categories12.add(blooz);
        categories12.add(bloozW);

        product12.setCode("7");
        product12.setName("بلوز زنانه ساده");
        product12.setDescription("توضیحات برای لباس مورد نظر، طرح آن و جنس آن.");
        product12.setBrand(brand2);
        product12.setCategories(categories12);
        product12.setMainCategory(bloozW);
        product12.setMainImage(booloozZanane);
        product12.setColor(white);
        product12.setPrice(1000000);
        product12.setOffPercent(10);
        product12.setMaterial("پشم");
        product12.setHeight(65);
        product12.setPattern("طرحدار");
        productService.save(product12);

        Inventory inventory23 = new Inventory();
        inventory23.setQuantity(10);
        inventory23.setProduct(product12);
        inventory23.setSize(Size.XL2);
        inventoryService.save(inventory23);

        Inventory inventory24 = new Inventory();
        inventory24.setQuantity(10);
        inventory24.setProduct(product12);
        inventory24.setSize(Size.XL);
        inventoryService.save(inventory24);


        //13
        Product product13 = new Product();
        List<Category> categories13 = new ArrayList<>();
        categories13.add(digikala);
        categories13.add(baft);
        categories13.add(baftW);

        product13.setCode("1");
        product13.setName("بافت زنانه کوتاه");
        product13.setDescription("توضیحات برای لباس مورد نظر، طرح آن و جنس آن.");
        product13.setBrand(brand2);
        product13.setCategories(categories13);
        product13.setMainCategory(baftW);
        product13.setMainImage(baftSabz);
        product13.setColor(green);
        product13.setPrice(1000000);
        product13.setOffPercent(10);
        product13.setMaterial("پشم");
        product13.setHeight(65);
        product13.setPattern("طرحدار");
        productService.save(product13);

        Inventory inventory25 = new Inventory();
        inventory25.setQuantity(10);
        inventory25.setProduct(product13);
        inventory25.setSize(Size.XL2);
        inventoryService.save(inventory25);

        Inventory inventory26 = new Inventory();
        inventory26.setQuantity(10);
        inventory26.setProduct(product13);
        inventory26.setSize(Size.XL);
        inventoryService.save(inventory26);


    }
}
