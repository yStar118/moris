package com.searchVO;

/**
 * Created by 1553280431@qq.com on 2017/7/9.
 * 检查对象
 */
public class InspectionObjectSearchVO extends CommonSearchVO {

    private String name;
    private String equipmentName;

    public String getName() {
        return name;
    }

    /**
     * 这里拼接模糊查询的字段值
     *
     * @return 字段值
     */
    public String getNameParam() {
        return "%" + name + "%";
    }

    public String getEquipmentNameParam() {
        return "%" + equipmentName + "%";
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEquipmentName() {
        return equipmentName;
    }

    public void setEquipmentName(String equipmentName) {
        this.equipmentName = equipmentName;
    }
}
