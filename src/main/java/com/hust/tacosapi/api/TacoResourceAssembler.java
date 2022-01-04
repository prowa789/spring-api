package com.hust.tacosapi.api;

import com.hust.tacosapi.entity.Taco;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;

public class TacoResourceAssembler
        extends RepresentationModelAssemblerSupport<Taco,TacoResource> {

    public TacoResourceAssembler() {
        super(DesignTacoController.class, TacoResource.class);
    }

    /*
    * Optinal nếu TacoResource có defaut constructor
    * Tuy nhiên trong trường hợp này TacoResource bắt buộc cần Taco để tạo ra nó
    * --> nên bạn cần ghi đè phương thức này
     */
    @Override
    protected TacoResource instantiateModel(Taco taco) {
        return new TacoResource(taco);
    }
    /*
     * Phương thức toModel là bắt buộc phải ghi đè khi
     * extend RepresentationModelAssemblerSupport
     * Ở đây, bạn đang yêu cầu nó tạo đối tượng TacoResource từ
     * Taco và tự động cung cấp cho nó một liên kết tự với URL được
     * lấy từ thuộc tính id của đối tượng Taco
     *
     * Bề ngoài, toModel() dường như có mục đích tương tự như InstantiateModel(),
     * nhưng chúng phục vụ các mục đích khác nhau một chút. Trong khi InstantiateModel()
     * chỉ nhằm khởi tạo một đối tượng Resource, thì toModel() không chỉ nhằm mục đích
     * để tạo đối tượng Resource, tự động cung cấp cho nó một liên kết tự với URL được
     * lấy từ thuộc tính id của đối tượng Taco
     * Dưới vỏ bọc, toResource () sẽ gọi InstantiateResource ()
     */
    @Override
    public TacoResource toModel(Taco taco) {
        return createModelWithId(taco.getId(), taco);
    }

}
