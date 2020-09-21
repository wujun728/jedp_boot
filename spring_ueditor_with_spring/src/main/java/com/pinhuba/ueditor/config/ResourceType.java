package com.pinhuba.ueditor.config;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class ResourceType {

	/** The name of the resource type */
	private String name;
	/** The allowed extensions */
	private Set<String> allowedEextensions;

	/** Map holding a String to ResourceType reference */
	private static Map<String, ResourceType> types = new HashMap<String, ResourceType>(3);

	public static final ResourceType FILE = new ResourceType("File", Utils.getSet(PropertiesLoader.getFileResourceTypeAllowedExtensions()));

	public static final ResourceType IMAGE = new ResourceType("Image", Utils.getSet(PropertiesLoader.getImageResourceTypeAllowedExtensions()));

	public static final ResourceType MEDIA = new ResourceType("Media", Utils.getSet(PropertiesLoader.getMediaResourceTypeAllowedExtensions()));

	static {
		types.put(FILE.getName(), FILE);
		types.put(IMAGE.getName(), IMAGE);
		types.put(MEDIA.getName(), MEDIA);
	}

	private ResourceType(final String name, final Set<String> allowedEextensions) {
		this.name = name;

		if (allowedEextensions.isEmpty())
			throw new IllegalArgumentException("AllowedEextensions shouldn't be empty!");

		this.allowedEextensions = allowedEextensions;
	}

	public String getName() {
		return name;
	}

	/**
	 * Returns a read-only reference to the allowed extensions set.
	 * 
	 * @return the read-only allowed extensions set of this resource type
	 */
	public Set<String> getAllowedEextensions() {
		return Collections.unmodifiableSet(allowedEextensions);
	}

	/**
	 * Returns the resource type constant with the specified name.
	 * 
	 * @param name
	 *            the name of the constant to return
	 * @return the resource type constant with the specified name
	 * @throws IllegalArgumentException
	 *             if this class has no constant with the specified name
	 * @throws NullPointerException
	 *             if <code>name</code> is null or empty
	 */
	public static ResourceType valueOf(final String name) {
		if (Utils.isEmpty(name))
			throw new NullPointerException("Name is null or empty");

		ResourceType rt = types.get(name);
		if (rt == null)
			throw new IllegalArgumentException("No resource type const " + name);
		return rt;
	}

	/**
	 * Returns <code>true</code> if name represents a valid resource type
	 * constant.
	 * 
	 * @param name
	 *            the resource type to check
	 * @return <code>true</code> if name represents a valid resource type, else
	 *         <code>false</code>
	 */
	public static boolean isValidType(final String name) {
		// HashMap permits null keys, so it will return false in that case
		return types.containsKey(name);
	}

	/**
	 * Returns the resource type constant with the specified name. In contrast
	 * to {@link #valueOf(String)} it returns a null instead of throwing an
	 * exception if a resource type constant was not found.
	 * 
	 * @param name
	 *            the name of the constant to return
	 * @return the resource type constant with the specified name, else
	 *         <code>null</code>
	 */
	public static ResourceType getResourceType(final String name) {
		try {
			return ResourceType.valueOf(name);
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * Returns the resource type constant with the specified name. In contrast
	 * to {@link #getResourceType(String)} it returns {@link #FILE} instead of
	 * returning <code>null</code>.
	 * 
	 * @param name
	 *            the name of the constant to return
	 * @return the resource type constant with the specified name, else
	 *         <code>FILE</code>
	 */
	public static ResourceType getDefaultResourceType(final String name) {
		ResourceType rt = getResourceType(name);
		return rt == null ? FILE : rt;
	}

	public boolean isAllowedExtension(final String extension) {
		if (Utils.isEmpty(extension))
			return false;
		String ext = extension.toLowerCase();
		return allowedEextensions.contains(ext);
	}

	public boolean isDeniedExtension(final String extension) {
		return !isAllowedExtension(extension);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;

		if (obj == null || this.getClass() != obj.getClass())
			return false;

		final ResourceType rt = (ResourceType) obj;
		return name.equals(rt.getName());
	}

	@Override
	public int hashCode() {
		return name.hashCode();
	}
}
