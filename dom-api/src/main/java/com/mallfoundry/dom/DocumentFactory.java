package com.mallfoundry.dom;

public interface DocumentFactory {

    <E extends Element> E createElement(Class<E> elementClass);
}
